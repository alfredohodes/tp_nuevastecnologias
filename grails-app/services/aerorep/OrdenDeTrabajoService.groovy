package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class OrdenDeTrabajoService {

    def prepararOT(OrdenDeTrabajo ot) {
        ot.preparar()
    }
}
