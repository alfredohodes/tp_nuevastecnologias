package aerorep

class CompraRepuesto {

    Date fecha
    ProveedorRepuesto proveedor


   static hasMany = [
     detalles: DetalleCompraRepuesto,
   ]

    static constraints = {
    }
}
