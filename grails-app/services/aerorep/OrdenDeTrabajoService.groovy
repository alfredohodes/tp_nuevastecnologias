package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class OrdenDeTrabajoService {

    def reservarRepuestosRequeridos(OrdenDeTrabajo ot) {
        ot.reservarRepuestosRequeridos()
    }
}
