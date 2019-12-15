package aerorep

class OrdenDeTrabajoRepository {

    OrdenDeTrabajoRepository() {}
    
    OrdenDeTrabajo getById(Long id) {
        OrdenDeTrabajo.get(id)
    }

    def getAll() {
        OrdenDeTrabajo.findAll()
    }
}
