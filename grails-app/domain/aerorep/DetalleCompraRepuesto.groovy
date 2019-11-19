package aerorep

class DetalleCompraRepuesto {

    StockRepuesto stock
    Dinero precio
    static embedded = ['precio']

    static constraints = {
    }
}
