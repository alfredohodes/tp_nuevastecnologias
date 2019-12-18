package aerorep

class CompraRepuestoRepository {

    CompraRepuestoRepository() {}
    
    CompraRepuesto getById(Long id) {
        CompraRepuesto.get(id)
    }

    def getAll() {
        CompraRepuesto.findAll()
    }
}
