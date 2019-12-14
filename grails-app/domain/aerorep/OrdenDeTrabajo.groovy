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

        requerimiento.ot = this
        requerimientoRepuestos << requerimiento
    }

    void agregarReservaRepuesto(ReservaRepuesto reservaRepuesto) {

        if(estado ==  Estado.EJECUTADA) throw new IllegalStateException("no se pueden modificar requerimientos en una ot ejecutada")

        def requerimiento = requerimientoRepuestos.find{ req -> req.tipo == reservaRepuesto.disponibilidadRepuesto.tipo }
        if(!requerimiento) throw new IllegalArgumentException("se intenta agregar una reserva de un tipo para el que no hay requerimiento")

        requerimiento.agregarReservaRepuesto(reservaRepuesto)
    }

    /*
        Reserva todos los repuestos requeridos para esta OT
    */
    void preparar() {
        requerimientoRepuestos.each { req ->
            req.buscarYReservarRepuestos()
        }
    }

    boolean puedeSerEjecutada() {
        requerimientoRepuestos.every { req -> req.estaCumplido() }
    }

    /*
        Si la OT puede ser ejecutada setea su estado en EJECUTADA y devuelve una lista de repuestos requeridos y sus ubicaciones en el depósito
    */
    void ejecutar()
    {
        // TODO: Revisar si puedeSerEjecutada(). Si no -> Exception

        // TODO: Setear estado EJECUTADA

        // TODO: Devolver lista de repuestos necesarios y sus ubicaciones en el depósito
    } 

    /*
        El valor de una OT se calcula como el costo total de los repuestos más un porcentaje de ganancia sobre este monto
    */
    Dinero calcularValor(Float porcentajeGanancia)
    {
        Dinero costoRepuestos = requerimientoRepuestos.sum { requerimiento -> 
            requerimiento.reservasRepuestos.sum { reserva -> reserva.calcularPrecioRepuestosReservados() }
        }
        
        costoRepuestos * (1 + porcentajeGanancia / 100)
    }

    String toString(){
        "OT {$id} -> $estado"
    }
}
