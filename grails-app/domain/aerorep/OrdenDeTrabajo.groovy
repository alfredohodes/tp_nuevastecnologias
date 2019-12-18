package aerorep

class OrdenDeTrabajo {

    Set<RequerimientoRepuesto> requerimientoRepuestos = []
    static hasMany = [
        requerimientoRepuestos: RequerimientoRepuesto,
    ]

    enum Estado {
        NO_EJECUTADA,
        EJECUTADA
    }
    Estado estado = Estado.NO_EJECUTADA

    static constraints = {
    }

    void agregarRequerimiento(RequerimientoRepuesto requerimiento) {

        if(estado ==  Estado.EJECUTADA) throw new IllegalStateException("no se pueden modificar requerimientos en una ot ejecutada")

        requerimiento.setOt(this)
        requerimientoRepuestos << requerimiento
    }

    void agregarReservaRepuesto(ReservaRepuesto reservaRepuesto) {

        if(estado ==  Estado.EJECUTADA) throw new IllegalStateException("no se pueden modificar requerimientos en una ot ejecutada")

        def requerimiento = requerimientoRepuestos.find{ req -> req.tipo == reservaRepuesto.disponibilidadRepuesto.tipo }
        if(!requerimiento) throw new IllegalArgumentException("se intenta agregar una reserva de un tipo para el que no hay requerimiento")

        requerimiento.agregarReservaRepuesto(reservaRepuesto)
    }

    boolean puedeSerEjecutada() {
        estado != Estado.EJECUTADA && requerimientoRepuestos.every { req -> req.estaCumplido() }
    }

    /*
        Si la OT puede ser ejecutada setea su estado en EJECUTADA y devuelve una lista de repuestos requeridos y sus ubicaciones en el depósito
    */
    void ejecutar()
    {
        // Podría llamar a puedeSerEjecutada() pero de esta manera se tienen excepciones más descriptivas
        if(estado ==  Estado.EJECUTADA) throw new IllegalStateException("la ot ya se encuentra ejecutada")
        if(!requerimientoRepuestos.every { req -> req.estaCumplido() }) throw new IllegalStateException("hay requerimientos de repuestos sin disponibilidad reservada")
        
        setEstado(Estado.EJECUTADA)

        // TODO: Devolver lista de repuestos necesarios y sus ubicaciones en el depósito
    }
    

    String toString() {

        "OT {$id}"
    }
}
