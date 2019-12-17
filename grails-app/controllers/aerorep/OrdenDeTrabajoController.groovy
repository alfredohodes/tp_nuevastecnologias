package aerorep

class OrdenDeTrabajoController {

    static scaffold = OrdenDeTrabajo

    def ordenDeTrabajoService

    def prepararOT(Long otId) {
        ordenDeTrabajoService.prepararOT(otId)
        OrdenDeTrabajo ot = ordenDeTrabajoService.getOrdenDeTrabajoById(otId)
        if(ot.puedeSerEjecutada()) {
            flash.message = "OT $otId Preparada"
        } else {
            flash.message = "OT $otId Preparada parcialmente. No se pudo reservar la totalidad de los repuestos requeridos. La OT no puede ser ejecutada."
        }
        redirect(action:'show', params:[id:otId])
    }

    def ejecutarOT(Long otId) {
        ordenDeTrabajoService.ejecutarOT(otId)
        flash.message = "OT $otId Ejecutada"
        redirect(action:'show', params:[id:otId])
    }

    def index() {
        def ots = ordenDeTrabajoService.getAllOrdenesDeTrabajo()
        [ordenDeTrabajoList: ots, ordenDeTrabajoCount: ots.size()]
    }

    def show() {
        OrdenDeTrabajo ot = ordenDeTrabajoService.getOrdenDeTrabajoById(params.long('id'))
        def reservasRepuestos = ordenDeTrabajoService.getReservasRepuestosParaOT(params.long('id'))
        [   ordenDeTrabajo: ot,
            puedeSerEjecutada:ot.puedeSerEjecutada(),
            estaEjecutada:ot.estado == OrdenDeTrabajo.Estado.EJECUTADA,
            reservasRepuestos:reservasRepuestos,
            valorOT:ordenDeTrabajoService.calcularValorOT(ot.id, 30)
        ]
    }
}
