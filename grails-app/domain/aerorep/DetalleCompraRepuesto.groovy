package aerorep

//@groovy.transform.ToString(excludes='repuestos,compra')
class DetalleCompraRepuesto {
  
  Dinero precio = new Dinero(0)
  static embedded = ['precio']

  Set<DisponibilidadRepuesto> repuestos = []
  static hasMany = [
    repuestos: DisponibilidadRepuesto,
  ]
  
  static belongsTo = [compra: CompraRepuesto]

  static constraints = {
  }

  Dinero getPrecioPorUnidad() {
    precio / getCantidadTotalRepuestos()
  }

  Integer getCantidadTotalRepuestos() {
    repuestos.cantidad.sum()
  }

  TipoRepuesto getTipoRepuesto() {
    repuestos.first().tipo
  }

  void agregarDisponibilidadRepuesto(DisponibilidadRepuesto repuesto) {
    repuestos << repuesto
  }

  String toString(){
    "DetalleCompra {$id}"
  }
  
}
