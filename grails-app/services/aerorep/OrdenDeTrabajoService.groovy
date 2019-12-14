package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class OrdenDeTrabajoService {

    def prepararOT(Long id) {
        OrdenDeTrabajo.get(id).preparar()
    }
}
