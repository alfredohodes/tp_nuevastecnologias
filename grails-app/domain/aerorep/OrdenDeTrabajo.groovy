package aerorep

//@groovy.transform.ToString(excludes='requerimientoRepuestos')
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

        // TODO: si existe ya req para cant, reusar. Si no, agregar

        requerimiento.ot = this
        requerimientoRepuestos << requerimiento
    }

    void agregarReservaRepuesto(ReservaRepuesto reservaRepuesto) {

        if(estado ==  Estado.EJECUTADA) throw new IllegalStateException("no se pueden modificar requerimientos en una ot ejecutada")

        def requerimiento = requerimientoRepuestos.find{ req -> req.tipo == reservaRepuesto.disponibilidadRepuesto.tipo }
        if(!requerimiento) throw new IllegalArgumentException("se intenta agregar una reserva de un tipo para el que no hay requerimiento")

        requerimiento.agregarReservaRepuesto(reservaRepuesto)
    }

    void reservarRepuestosRequeridos() {
        requerimientoRepuestos.each { req ->
            req.buscarYReservarRepuestos()
        }
    }

    boolean puedeSerEjecutada() {

        // TODO: Comparar los repuestos asignados con los requeridos

        // TODO: Revisar que no haya repuestos vencidos
    }

    void ejecutar()
    {
        // TODO: Revisar si puedeSerEjecutada(). Si no -> Exception

        // TODO: Setear estado EJECUTADA

        // TODO: Devolver lista de repuestos necesarios y sus ubicaciones en el depósito
    } 

    Dinero calcularValor(Float porcentajeGanancia)
    {
        // TODO: Calcular el costo de la compra de los repuestos reservados al momento y sumarle el porcentaje de ganancia
    }

  String toString(){
    "OT {$id} -> $estado"
  }
}
