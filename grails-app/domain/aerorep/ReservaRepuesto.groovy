package aerorep

class ReservaRepuesto {

    Integer cantidad
    DisponibilidadRepuesto disponibilidadRepuesto

    static belongsTo = [requerimiento: RequerimientoRepuesto]

    static constraints = {
    }

    String toString(){
      "{$id}: $disponibilidadRepuesto.tipo.nombre x $cantidad"
    }
}
