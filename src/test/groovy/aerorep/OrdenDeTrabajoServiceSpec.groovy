package aerorep

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class OrdenDeTrabajoServiceSpec extends Specification implements ServiceUnitTest<OrdenDeTrabajoService>, DataTest {

    void setupSpec() { 
        mockDomain ProveedorRepuesto
        mockDomain TipoRepuesto
        mockDomain CompraRepuesto
        mockDomain DetalleCompraRepuesto
        mockDomain DisponibilidadRepuesto
        mockDomain RequerimientoRepuesto
        mockDomain OrdenDeTrabajo
    }

    def setup() {
        // Proveedor de tornillos
        new ProveedorRepuesto(cuit:"11-11111111-0", razonSocial: "VendeTornillo SRL").save()
        
        // Tipo repuesto: Tornillo
        new TipoRepuesto(nombre:"Tornillo", codigo:"TOR-1" + System.currentTimeMillis().toString(), cantidadAlertaStockMinimo:100).save()
    }
    
    void crearCompra100TornillosEnDosUbicaciones() {
        
        ProveedorRepuesto provTornillos = ProveedorRepuesto.get(1)
        
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        
        CompraRepuesto compra = new CompraRepuesto(proveedor:provTornillos, fecha:new Date())
        DetalleCompraRepuesto detalleTornillos = new DetalleCompraRepuesto(precio:new Dinero(200))
        DisponibilidadRepuesto disponibilidadTornillos70 = new DisponibilidadRepuesto(tipo:tipoTornillo,
                                                                                            ubicacion:new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"),
                                                                                            cantidad:70,
                                                                                            numeroDeSerie:"N/A",
                                                                                            lote:"QWERTYASD",
                                                                                            vencimiento:new Date(System.currentTimeMillis()+10000000000)
                                                                                            ).save()
        DisponibilidadRepuesto disponibilidadTornillos30 = new DisponibilidadRepuesto(tipo:tipoTornillo,
                                                                                            ubicacion:new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 2", espacio:"Esp 2"),
                                                                                            cantidad:30,
                                                                                            numeroDeSerie:"N/A",
                                                                                            lote:"QWERTYASD",
                                                                                            vencimiento:new Date(System.currentTimeMillis()+30000000000)
                                                                                            ).save()
        detalleTornillos.agregarDisponibilidadRepuesto(disponibilidadTornillos70)
        detalleTornillos.agregarDisponibilidadRepuesto(disponibilidadTornillos30)
        compra.agregarDetalle(detalleTornillos)
        compra.save()
    }
    
    void crearOTQueRequiere50Tornillos() {
        OrdenDeTrabajo ot = new OrdenDeTrabajo()
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        RequerimientoRepuesto reqNuevoTornillos = new RequerimientoRepuesto(tipo:tipoTornillo, cantidad:50)
        ot.agregarRequerimiento(reqNuevoTornillos)
        ot.save(flush:true)
    }

    def cleanup() {
    }

    void "test data inicial uno"() {
        
        given: 'Proveedores y compras creados'
        crearCompra100TornillosEnDosUbicaciones()
        crearOTQueRequiere50Tornillos()

        when: 'No pasa nada'
        def a = 1

        then: 'Están disponibles los 100 tornillos cargados'
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        def todasLasDisponibilidades = DisponibilidadRepuesto.findAllByTipo(tipoTornillo)
        def cantDisponible = todasLasDisponibilidades.inject(0) { cantDisp, disp -> cantDisp + disp.cantidad - disp.cantidadReservada}
        cantDisponible == 100
    }

    void "test data inicial dos"() {
        
        given: 'Proveedores y compras creados'
        crearCompra100TornillosEnDosUbicaciones()
        crearOTQueRequiere50Tornillos()

        when: 'Reserva repuestos'
        service.reservarRepuestosRequeridos(OrdenDeTrabajo.get(1))

        then: 'Baja la disponibilidad de tornillos'
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        def todasLasDisponibilidades = DisponibilidadRepuesto.findAllByTipo(tipoTornillo)
        def cantDisponible = todasLasDisponibilidades.inject(0) { cantDisp, disp -> cantDisp + disp.cantidad - disp.cantidadReservada}
        cantDisponible == 50
    }
}
