package aerorep

class RequerimientoRepuesto {

    final TipoRepuesto tipoRepuesto
    final Integer cantidad

    static constraints = {
        tipoRepuesto nullable: false
        cantidad min: 1
    }
}
