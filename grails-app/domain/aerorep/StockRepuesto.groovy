package aerorep

class StockRepuesto {

    DetalleCompraRepuesto detalleCompra
    static hasMany = [
     repuestos: Repuesto,
   ]

    static constraints = {
    }
}
