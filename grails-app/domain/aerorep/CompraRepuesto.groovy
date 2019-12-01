package aerorep

//@groovy.transform.ToString(excludes='detalles')
class CompraRepuesto {

    Date fecha
    ProveedorRepuesto proveedor

    Set<DetalleCompraRepuesto> detalles = []
    static hasMany = [
      detalles: DetalleCompraRepuesto,
    ]

    static constraints = {
    }
}
