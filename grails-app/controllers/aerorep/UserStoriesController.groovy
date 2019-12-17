package aerorep

class UserStoriesController {

    def index() { }

    /*
    reserva con repuestos suficientes
    */
    def prepararUS1_1() {

        eliminarTodasLasComprasYOrdenesDeTrabajo()

        //'Hay 100 tornillos disponibles con dos fechas de vencimiento distinta'
        Date vencimientoPrimero = new Date().plus(30)
        Date vencimientoSegundo = new Date().plus(60)
        crearCompraTornillos(50, new Dinero(99.99), vencimientoPrimero, "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))
        crearCompraTornillos(50, new Dinero(99.99), vencimientoSegundo, "LOTE-X11", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 2"))
        
        //'La OT en preparación requiere 10 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(10)

        flash.message = "US 1.1 preparada"
        redirect(uri:'/')
    }

    /*
    reserva con repuestos insuficientes
    */
    def prepararUS1_2() {

        eliminarTodasLasComprasYOrdenesDeTrabajo()

        //'Hay 5 tornillos disponibles'
        crearCompraTornillos(5, new Dinero(99.99), new Date().plus(30), "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))
        
        //'La OT en preparación requiere 10 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(10)

        flash.message = "US 1.2 preparada"
        redirect(uri:'/')
    }

    /*
    determinacion valor ot
    */
    def prepararUS2() {

        eliminarTodasLasComprasYOrdenesDeTrabajo()

        // 'Hay 10 tornillos comprados a 10 pesos cada uno'
        crearCompraTornillos(10, new Dinero(100), new Date().plus(30), "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))
        
        //'La OT en preparación requiere 10 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(10)

        flash.message = "US 2 preparada"
        redirect(uri:'/')
    }

    /*
    envía alerta stock mínimo
    */
    def prepararUS3_1() {

        eliminarTodasLasComprasYOrdenesDeTrabajo()

        // 'El stock mínimo para alerta de Tornillos es de 1000 unidades'
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        tipoTornillo.cantidadAlertaStockMinimo = 1000
        tipoTornillo.save()

        // 'Hay 1050 tornillos disponibles'
        crearCompraTornillos(1050, new Dinero(10500), new Date().plus(30), "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))

        // 'La OT en preparación requiere 100 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(100)

        flash.message = "US 3.1 preparada"
        redirect(uri:'/')
    }

    /*
    no envía alerta stock mínimo por ya estar por debajo inicialmente
    */
    def prepararUS3_2() {

        eliminarTodasLasComprasYOrdenesDeTrabajo()

        // 'El stock mínimo para alerta de Tornillos es de 1000 unidades'
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        tipoTornillo.cantidadAlertaStockMinimo = 1000
        tipoTornillo.save()

        // 'Hay 900 tornillos disponibles'
        crearCompraTornillos(900, new Dinero(9000), new Date().plus(30), "LOTE-X10", new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"))

        // 'La OT en preparación requiere 100 tornillos'
        OrdenDeTrabajo ot = crearOTQueRequiereTornillos(100)

        flash.message = "US 3.2 preparada"
        redirect(uri:'/')
    }


    private def crearCompraTornillos(Integer cantidad, Dinero precio, Date vencimiento, String lote, UbicacionAlmacenamiento ubicacion) {
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

    private def crearOTQueRequiereTornillos(Integer cantTornillosRequeridos) {
        OrdenDeTrabajo ot = new OrdenDeTrabajo()
        TipoRepuesto tipoTornillo = TipoRepuesto.get(1)
        RequerimientoRepuesto reqNuevoTornillos = new RequerimientoRepuesto(tipo:tipoTornillo, cantidad:cantTornillosRequeridos)
        ot.agregarRequerimiento(reqNuevoTornillos)
        ot.save(flush:true)
        ot
    }

    private def eliminarTodasLasComprasYOrdenesDeTrabajo() {
        ReservaRepuesto.findAll().each { it.delete(flush:true, failOnError:true) }
        CompraRepuesto.findAll().each { it.delete(flush:true, failOnError:true) }
        DetalleCompraRepuesto.findAll().each { it.delete(flush:true, failOnError:true) }
        DisponibilidadRepuesto.findAll().each { it.delete(flush:true, failOnError:true) }
        OrdenDeTrabajo.findAll().each { it.delete(flush:true, failOnError:true) }
        RequerimientoRepuesto.findAll().each { it.delete(flush:true, failOnError:true) }
    }
}
