package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class NotificacionService {

    def notificar(String msj) {
        println "Te notifico que $msj"
    }
}
