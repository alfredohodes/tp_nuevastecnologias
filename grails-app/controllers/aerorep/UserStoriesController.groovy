package aerorep

class UserStoriesController {

    def index() { }

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
