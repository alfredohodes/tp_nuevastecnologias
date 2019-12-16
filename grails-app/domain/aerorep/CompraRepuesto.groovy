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

    void agregarDetalle(DetalleCompraRepuesto detalle) {
      detalle.setCompra(this)
      detalles << detalle
    }

    String toString(){
      "Compra {$id}"
    }
}
