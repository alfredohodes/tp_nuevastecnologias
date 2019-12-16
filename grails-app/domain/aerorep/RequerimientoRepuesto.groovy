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
        reserva.setRequerimiento(this)
    }

    void quitarReservaRepuesto(ReservaRepuesto reserva)
    {
        if(ot.estado ==  OrdenDeTrabajo.Estado.EJECUTADA) throw new IllegalStateException("no se pueden quitar reservas en una ot ejecutada")
        reservasRepuestos -= reserva
    }

    void quitarReservaRepuestos() {

        // TODO: No permitir si OT tiene estado EJECUTADA

        // TODO: Quitar el repuesto de la lista de repuestos asignados.
    }

    /**
        Valida que todos los repuestos requeridos estén reservados y que ninguna reserva contenga repuestos vencidos
    */
    boolean estaCumplido() {

        def cantPendienteAReservar = cantidad - reservasRepuestos.cantidad.sum(0)
        def ningunRepuestoVencido = reservasRepuestos.every{ reserva -> !reserva.disponibilidadRepuesto.estaVencido() }
        
        cantPendienteAReservar == 0 && ningunRepuestoVencido
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

    Integer getCantidadPendienteAReservar() {
        cantidad - reservasRepuestos.cantidad.sum(0)
    }


  String toString() {
    "RequerimientoRepuesto {$id} -> $tipo.nombre x $cantidad"
  }
}
