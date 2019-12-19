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
    }
    

    String toString() {

        "OT {$id}"
    }
}
