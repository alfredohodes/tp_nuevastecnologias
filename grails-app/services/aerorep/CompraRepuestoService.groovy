package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class CompraRepuestoService {

    def compraRepuestoRepository

    def getCompraRepuestoById(Long otId) {
        compraRepuestoRepository.getById(otId)
    }
}
