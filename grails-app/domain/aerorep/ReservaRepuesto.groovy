package aerorep

class ReservaRepuesto {

    Integer cantidad
    DisponibilidadRepuesto disponibilidadRepuesto

    static belongsTo = [requerimiento: RequerimientoRepuesto]

    static constraints = {
    }

    Dinero calcularPrecioRepuestosReservados()
    {
        disponibilidadRepuesto.getPrecioPorUnidad() * cantidad
    }

  String toString(){
    "ReservaRepuesto {$id} -> $disponibilidadRepuesto.tipo.nombre x $cantidad"
  }
}
