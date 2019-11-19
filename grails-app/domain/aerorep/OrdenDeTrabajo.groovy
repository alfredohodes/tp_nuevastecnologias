package aerorep

class OrdenDeTrabajo {

   static hasMany = [
     requerimientoRepuestos: RequerimientoRepuesto,
     repuestosReservados: Repuesto,
   ]

    enum Estado {
        EN_PREPARACION,
        PREPARADA,
        EJECUTADA
    }
    Estado estado

    static constraints = {
    }

    void reservarRepuesto(Repuesto repuesto) {

        // TODO: Si no hay un requerimiento para este repuesto -> Exception

        // TODO: Agregar el repuesto a la lista de repuestos reservados.

        // TODO: Si al agregar este repuesto puedeSerEjecutada() -> setear estado PREPARADA
    }

    void quitarReservaRepuesto(Repuesto repuesto) {

        // TODO: Quitar el repuesto de la lista de repuestos asignados.

        // TODO: Setear estado EN_PREPARACION
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
