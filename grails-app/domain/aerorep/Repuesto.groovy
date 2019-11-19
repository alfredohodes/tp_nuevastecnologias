package aerorep

class Repuesto {

    TipoRepuesto tipo

    UbicacionAlmacenamiento ubicacion
    static embedded = ['ubicacion']

    OrdenDeTrabajo ordenDeTrabajo

    Integer cantidad

    enum Estado {
        DISPONIBLE,
        RESERVADO,
        VENCIDO
    }
    Estado estado

    Date vencimiento

    static constraints = {
        tipo nullable: false
        cantidad min: 1
    }


    // Método que desdobla un repuesto con cantidad > 1 en dos repuestos (cada uno con su cantidad)
    // Sirve, por ejemplo, para cuando se tiene un repuesto con cantidad 100 en una ubicación (e.g., tuercas) y se
    // necesita separar 40 de ellos para reservar para una OT. Se pasa de una instancia de Repuesto con estado DISPONIBLE y
    // cantidad 100 a dos instancias de Repuesto, una DISPONIBLE con cantidad 60 y otra RESERVADO con cantidad 40.
    Repuesto separarCantidadEnNuevoRepuesto(Integer cantidadASeparar) {

        // TODO: Si cantidadASeparar <= 0 -> Exception ("cantidadASeparar tiene que ser mayor a 0")

        // TODO: Si cantidadASeparar >= cantidad -> Exception ("No hay cantidad suficiente")
    }

    void reservarParaOT(Integer cantidad, OrdenDeTrabajo ot) {

        // TODO: Setear estado RESERVADO

        // TODO: Setear ordenDeTrabajo
    }

}
