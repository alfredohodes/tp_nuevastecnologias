package aerorep

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class OrdenDeTrabajoServiceSpec extends Specification implements ServiceUnitTest<OrdenDeTrabajoService>, DataTest {
    
    def setupSpec() { 
        mockDomain ProveedorRepuesto
        mockDomain TipoRepuesto
        mockDomain CompraRepuesto
        mockDomain DetalleCompraRepuesto
        mockDomain DisponibilidadRepuesto
        mockDomain RequerimientoRepuesto
        mockDomain OrdenDeTrabajo
    }

    def setup() {
        service.disponibilidadRepuestoService = new DisponibilidadRepuestoService (
            disponibilidadRepuestoRepository:new DisponibilidadRepuestoRepository(),
            notificacionService:new NotificacionService()
        )

        // Proveedor de tornillos
        new ProveedorRepuesto(cuit:"11-11111111-0", razonSocial: "VendeTornillo SRL").save()
        
        // Tipo repuesto: Tornillo
        new TipoRepuesto(nombre:"Tornillo", codigo:"TOR-1" + System.currentTimeMillis().toString(), cantidadAlertaStockMinimo:100).save()
    }

    def crearCompraTornillos(Integer cantidad, Dinero precio, Date vencimiento, String lote, UbicacionAlmacenamiento ubicacion) {
        ProveedorRepuesto provTornillos = ProveedorRepuesto.get(1)
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        
        CompraRepuesto compra = new CompraRepuesto(proveedor:provTornillos, fecha:new Date())
        DetalleCompraRepuesto detalleTornillos = new DetalleCompraRepuesto(precio: precio)
        DisponibilidadRepuesto disponibilidadTornillos = new DisponibilidadRepuesto(tipo:tipoTornillo,
                                                                                            ubicacion:ubicacion,
                                                                                            cantidad:cantidad,
                                                                                            numeroDeSerie:"N/A",
                                                                                            lote:lote,
                                                                                            vencimiento:vencimiento
                                                                                            ).save()
        detalleTornillos.agregarDisponibilidadRepuesto(disponibilidadTornillos)
        compra.agregarDetalle(detalleTornillos)
        compra.save()
        compra
    }

    def crearOTQueRequiereTornillos(Integer cantTornillosRequeridos) {
        OrdenDeTrabajo ot = new OrdenDeTrabajo()
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        RequerimientoRepuesto reqNuevoTornillos = new RequerimientoRepuesto(tipo:tipoTornillo, cantidad:cantTornillosRequeridos)
        ot.agregarRequerimiento(reqNuevoTornillos)
        ot.save(flush:true)
        ot
    }

    def cleanup() {
    }


    void "historia 1.1 - reserva con repuestos suficientes"() {
        given: 'Hay 100 tornillos disponibles con dos fechas de vencimiento distinta'
        Date vencimientoPrimero = new Date().plus(30)
        Date vencimientoSegundo = new Date().plus(60)
        crearCompraTornillos(50, new Dinero(99.99), vencimientoPrimero, "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))
        crearCompraTornillos(50, new Dinero(99.99), vencimientoSegundo, "LOTE-X11", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 2"))
        
        and: 'La OT en preparación requiere 10 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(10)

        when: 'Prepara OT'
        service.prepararOT(ot.id)

        then: 'La cantidad de tornillos disponible es 90'
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        def todasLasDisponibilidades = DisponibilidadRepuesto.findAllByTipo(tipoTornillo)
        def cantDisponible = todasLasDisponibilidades.inject(0) { cantDisp, disp -> cantDisp + disp.cantidad - disp.cantidadReservada}
        cantDisponible == 90

        and: 'Se reservaron 10 tornillos de la disponibilidad más próxima a vencer'
        def todasLasDisponibilidadesReservadas = todasLasDisponibilidades.findAll { disp -> disp.cantidadReservada > 0}
        todasLasDisponibilidadesReservadas.size() == 1
        todasLasDisponibilidadesReservadas.every { disp -> disp.vencimiento = vencimientoPrimero }

        and: 'La OT puede ser ejecutada'
        ot.puedeSerEjecutada()
    }

    void "historia 1.2 - reserva con repuestos insuficientes"() {
        
        given: 'Hay 5 tornillos disponibles'
        crearCompraTornillos(5, new Dinero(99.99), new Date().plus(30), "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))
        
        and: 'La OT en preparación requiere 10 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(10)

        when: 'Prepara OT'
        service.prepararOT(ot.id)
        
        then: 'La OT no puede ser ejecutada'
        !ot.puedeSerEjecutada()
    }

    void "historia 2.1 - reserva con repuestos insuficientes"() {
        
        given: 'Se dispone de 10 tornillos comprados a 10 pesos cada uno'
        crearCompraTornillos(10, new Dinero(100), new Date().plus(30), "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))
        
        and: 'La OT en preparación requiere 10 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(10)

        when: 'Prepara OT'
        service.prepararOT(ot.id)
        
        then: 'El valor de la OT con 30 porciento de ganancia es 130 pesos'
        service.calcularValorOT(ot.id, 30).monto == 130
    }

    void "historia 3.1 - reserva dispara alerta de stock mínimo"() {
        
        given: 'El stock mínimo para alerta de Tornillos es de 1000 unidades'
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        tipoTornillo.cantidadAlertaStockMinimo = 1000
        tipoTornillo.save()

        and: 'Hay 1050 tornillos disponibles'
        crearCompraTornillos(1050, new Dinero(10500), new Date().plus(30), "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))

        and: 'La OT en preparación requiere 100 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(100)

        service.disponibilidadRepuestoService.notificacionService = Mock(NotificacionService)

        when: 'Prepara OT'
        service.prepararOT(ot.id)
        
        then: 'La cantidad de tornillos disponible es 950'
        def todasLasDisponibilidades = DisponibilidadRepuesto.findAllByTipo(tipoTornillo)
        def cantDisponible = todasLasDisponibilidades.inject(0) { cantDisp, disp -> cantDisp + disp.cantidad - disp.cantidadReservada}
        cantDisponible == 950

        and: 'Se envió alerta de stock mínimo'
        1 * service.disponibilidadRepuestoService.notificacionService.notificar(_)
    }

    void "historia 3.2 - reserva no dispara alerta de stock mínimo porque ya estaba por debajo"() {
        
        given: 'El stock mínimo para alerta de Tornillos es de 1000 unidades'
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        tipoTornillo.cantidadAlertaStockMinimo = 1000
        tipoTornillo.save()

        and: 'Hay 900 tornillos disponibles'
        crearCompraTornillos(900, new Dinero(9000), new Date().plus(30), "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))

        and: 'La OT en preparación requiere 100 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(100)

        service.disponibilidadRepuestoService.notificacionService = Mock(NotificacionService)

        when: 'Prepara OT'
        service.prepararOT(ot.id)
        
        then: 'La cantidad de tornillos disponible es 800'
        def todasLasDisponibilidades = DisponibilidadRepuesto.findAllByTipo(tipoTornillo)
        def cantDisponible = todasLasDisponibilidades.inject(0) { cantDisp, disp -> cantDisp + disp.cantidad - disp.cantidadReservada}
        cantDisponible == 800

        and: 'No se envió alerta de stock mínimo'
        0 * service.disponibilidadRepuestoService.notificacionService.notificar(_)
    }
}
