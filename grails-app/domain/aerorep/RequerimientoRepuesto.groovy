package aerorep

class RequerimientoRepuesto {

    TipoRepuesto tipoRepuesto
    Integer cantidad
    static belongsTo = [ot: OrdenDeTrabajo]

    static constraints = {
        tipoRepuesto nullable: false
        cantidad min: 1
    }
}
