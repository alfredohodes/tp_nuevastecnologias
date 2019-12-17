package aerorep

class BootStrap {

    def disponibilidadRepuestoService

    def init = { servletContext ->

        // Proveedor de tornillos
        ProveedorRepuesto provTornillos = new ProveedorRepuesto(cuit:"11-11111111-1", razonSocial: "VendeTornillo SRL").save(failOnError: true)

        // Tipo repuesto: Tornillo
        TipoRepuesto tipoTornillo = new TipoRepuesto(nombre:"Tornillo", codigo:"TOR-1" + System.currentTimeMillis().toString(), cantidadAlertaStockMinimo:100).save(failOnError: true)


        // Compra de 100 tornillos a $2 cada uno, almacenados 70 en ubicación 1 y 30 en ubicación 2
        CompraRepuesto compra = new CompraRepuesto(proveedor:provTornillos, fecha:new Date())
        DetalleCompraRepuesto detalleTornillos = new DetalleCompraRepuesto(precio:new Dinero(200))
        DisponibilidadRepuesto disponibilidadTornillos70 = new DisponibilidadRepuesto(tipo:tipoTornillo,
                                                                                            ubicacion:new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 1", espacio:"Esp 1"),
                                                                                            cantidad:70,
                                                                                            numeroDeSerie:"N/A",
                                                                                            lote:"QWERTYASD",
                                                                                            vencimiento:new Date(System.currentTimeMillis()+30000000000)
                                                                                            ).save(failOnError: true)
        DisponibilidadRepuesto disponibilidadTornillos30 = new DisponibilidadRepuesto(tipo:tipoTornillo,
                                                                                            ubicacion:new UbicacionAlmacenamiento(deposito:"Dep 1", zona:"Zona 1", estanteria:"Est 2", espacio:"Esp 2"),
                                                                                            cantidad:30,
                                                                                            numeroDeSerie:"N/A",
                                                                                            lote:"QWERTYASD",
                                                                                            vencimiento:new Date(System.currentTimeMillis()+30000000000)
                                                                                            ).save(failOnError: true)
        detalleTornillos.agregarDisponibilidadRepuesto(disponibilidadTornillos70)
        detalleTornillos.agregarDisponibilidadRepuesto(disponibilidadTornillos30)

        compra.agregarDetalle(detalleTornillos)

        compra.save(failOnError: true)


        // Orden de trabajo previa que requiere 40 tornillos ya reservados de disponibilidad 1
        OrdenDeTrabajo otPrevia = new OrdenDeTrabajo()

        RequerimientoRepuesto reqPrevioTornillos = new RequerimientoRepuesto(tipo:tipoTornillo, cantidad:40)
        otPrevia.agregarRequerimiento(reqPrevioTornillos)
        otPrevia.save(failOnError: true, flush:true)

        disponibilidadRepuestoService.reservar(disponibilidadTornillos70.id, 40)


        // Orden de trabajo nueva que requiere 50 tornillos
        OrdenDeTrabajo otNueva = new OrdenDeTrabajo()

        RequerimientoRepuesto reqNuevoTornillos = new RequerimientoRepuesto(tipo:tipoTornillo, cantidad:50)
        otNueva.agregarRequerimiento(reqNuevoTornillos)
        otNueva.save(failOnError: true, flush:true)
    }
    
    def destroy = {
    }
}
