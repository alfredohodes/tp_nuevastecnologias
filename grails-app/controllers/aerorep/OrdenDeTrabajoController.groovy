package aerorep

class OrdenDeTrabajoController {

    static scaffold = OrdenDeTrabajo

    def ordenDeTrabajoService

    def prepararOT(Long otId) {
        println "otID $otId"
        ordenDeTrabajoService.prepararOT(otId)
        flash.message = "OT $otId Preparada"
        redirect(action:'index')
    }
}
