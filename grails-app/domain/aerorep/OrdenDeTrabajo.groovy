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

    void reservarRepuestosRequeridos() {

        // TODO: Iterar por todos los RequerimientoRepuesto y buscarYReservarRepuestos()
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
}
