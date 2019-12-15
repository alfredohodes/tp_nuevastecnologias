package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class TipoRepuestoService {

    def getTipoRepuestoByCodigo(String codigo){
        TipoRepuesto.findByCodigo(codigo)
    }
}
