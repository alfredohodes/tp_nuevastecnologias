package aerorep

class DetalleCompraRepuesto {
  
  Dinero precio
  static embedded = ['precio']

  static belongsTo = [compra: CompraRepuesto]

  static hasMany = [
    repuestos: DisponibilidadRepuesto,
  ]

  static constraints = {
  }
  
}
