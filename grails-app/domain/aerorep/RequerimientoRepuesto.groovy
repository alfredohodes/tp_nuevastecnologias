package aerorep

//@groovy.transform.ToString(excludes='reservasRepuestos,ot')
class RequerimientoRepuesto {

    TipoRepuesto tipo

    Integer cantidad

    static belongsTo = [ot: OrdenDeTrabajo]

    Set<ReservaRepuesto> reservasRepuestos = []
    static hasMany = [
        reservasRepuestos: ReservaRepuesto,
    ]

    static constraints = {
        tipo nullable: false
        cantidad min: 1
    }

    void agregarReservaRepuesto(ReservaRepuesto reserva)
    {
        validarReserva(reserva)
        reservasRepuestos << reserva
        reserva.requerimiento = this
    }

    void quitarReservaRepuesto(ReservaRepuesto reserva)
    {
        if(ot.estado ==  OrdenDeTrabajo.Estado.EJECUTADA) throw new IllegalStateException("no se pueden quitar reservas en una ot ejecutada")
        reservasRepuestos -= reserva
    }

    void buscarYReservarRepuestos() {

        println "Buscando repuestos..."
        
        def todasLasDisponibilidades = DisponibilidadRepuesto.findAllByTipo(tipo, [sort: "vencimiento", order: "asc"])
        
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

    void validarReserva(ReservaRepuesto reserva)
    {
        println "Validando reserva..."
        if(reserva == null) throw new IllegalArgumentException("reserva es null")
        if(reserva.disponibilidadRepuesto == null) throw new IllegalArgumentException("reserva no tiene disponibilidad asignada")
        if(reserva.disponibilidadRepuesto.tipo != tipo) throw new IllegalArgumentException("reserva no es del tipo requerido")

        // Validar que no se esté reservando más de lo necesario
        def cantPendienteAReservar = cantidad - reservasRepuestos.cantidad.sum(0)
        if(reserva.cantidad > cantPendienteAReservar) throw new IllegalArgumentException("reserva es por una cantidad mayor a la pendiente de reservar")
    }

  String toString(){
    "RequerimientoRepuesto {$id} -> $tipo.nombre x $cantidad"
  }
}
