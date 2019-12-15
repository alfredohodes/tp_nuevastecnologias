package aerorep

class OrdenDeTrabajoController {

    static scaffold = OrdenDeTrabajo

    def ordenDeTrabajoService

    def listaOrdenesDeTrabajo() {
        def ots = ordenDeTrabajoService.getAllOrdenesDeTrabajo()
        [ots: ots]
    }
}
