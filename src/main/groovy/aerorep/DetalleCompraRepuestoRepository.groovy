package aerorep

class DetalleCompraRepuestoRepository {

    DetalleCompraRepuestoRepository() {}
    
    DetalleCompraRepuesto getById(Long id) {
        DetalleCompraRepuesto.get(id)
    }

    def getAll() {
        DetalleCompraRepuesto.findAll()
    }
}
