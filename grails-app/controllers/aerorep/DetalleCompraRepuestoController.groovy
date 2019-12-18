package aerorep

class DetalleCompraRepuestoController {

    static scaffold = DetalleCompraRepuesto

    def detalleCompraRepuestoRepository

    def show() {
        DetalleCompraRepuesto detalleCompraRepuesto = detalleCompraRepuestoRepository.getById(params.long('id'))
        [detalleCompraRepuesto: detalleCompraRepuesto, cantidadTotal:detalleCompraRepuesto.getCantidadTotalRepuestos(), tipoRepuesto:detalleCompraRepuesto.getTipoRepuesto()]
    }
}
