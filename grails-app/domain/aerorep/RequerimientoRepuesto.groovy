package aerorep

//@groovy.transform.ToString(excludes='reservasRepuestos,ot')
class RequerimientoRepuesto {

    TipoRepuesto tipoRepuesto

    Integer cantidad

    static belongsTo = [ot: OrdenDeTrabajo]

    Set<ReservaRepuesto> reservasRepuestos = []
    static hasMany = [
        reservasRepuestos: ReservaRepuesto,
    ]

    static constraints = {
        tipoRepuesto nullable: false
        cantidad min: 1
    }

    void buscarYReservarRepuestos() {

        println "Buscando repuestos..."
        
        def todasLasDisponibilidades = DisponibilidadRepuesto.findAllByTipo(tipoRepuesto, [sort: "vencimiento", order: "asc"])
        
        def disponibilidadesNoVencidasConCantidadesNoReservadas = todasLasDisponibilidades.findAll { disp ->
            !disp.estaVencido() && disp.cantidadReservada < disp.cantidad
        }
        
        disponibilidadesNoVencidasConCantidadesNoReservadas.each { disp ->
            println "disp: $disp || Cant: $disp.cantidad en $disp.ubicacion || Vto: $disp.vencimiento"

        }

        // Inject para ir reservando repuestos disponibles por orden de vencimiento. Acumulo la cant. que resta reservar
        def cantPendienteAReservar = disponibilidadesNoVencidasConCantidadesNoReservadas.inject(req.cantidad) { cantPendiente, disp ->
            def cantLibreEnDispActual = disp.cantidad - disp.cantidadReservada;
            println "cantLibreEnDispActual: $cantLibreEnDispActual || cantPendiente: $cantPendiente"
            def cantAReservar = [cantLibreEnDispActual, cantPendiente].min()
            println "disp: $disp || Cant: $cantLibreEnDispActual/$disp.cantidad || Pendiente: $cantPendiente || A reservar: $cantAReservar"
            
            // TODO: Agregar el repuesto a la lista de repuestos reservados.
            disp.cantidadReservada += 1
            disp.save(flush:true)
            cantPendiente-cantAReservar
        }
    }

    void quitarReservaRepuestos() {

        // TODO: No permitir si OT tiene estado EJECUTADA

        // TODO: Quitar el repuesto de la lista de repuestos asignados.
    }

    boolean estaCumplido() {

        // TODO: Comparar los repuestos asignados con los requeridos

        // TODO: Revisar que no haya repuestos vencidos
    }
}
