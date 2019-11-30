package aerorep

class RequerimientoRepuesto {

    TipoRepuesto tipoRepuesto

    Integer cantidad

    static belongsTo = [ot: OrdenDeTrabajo]

    static hasMany = [
     repuestosReservados: DisponibilidadRepuesto,
   ]

    static constraints = {
        tipoRepuesto nullable: false
        cantidad min: 1
    }

    void buscarYReservarRepuestos() {

        // TODO: Buscar repuestos no vencidos y no reservados

        // TODO: Agregar el repuesto a la lista de repuestos reservados.
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
