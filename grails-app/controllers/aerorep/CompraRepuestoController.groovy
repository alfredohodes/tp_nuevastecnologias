package aerorep

class CompraRepuestoController {

    static scaffold = CompraRepuesto

    def compraRepuestoService

    def show() {
        CompraRepuesto compra = compraRepuestoService.getCompraRepuestoById(params.long('id'))

        // calcular, para cada detalle, el tiporepuesto, la cantidad y el precio

        Set<DetalleCompraInfo> detalleInfos = compra.detalles.collect { detalleCompra ->
            Integer cantidadTotalDetalle = detalleCompra.getCantidadTotalRepuestos()
            new DetalleCompraInfo(detalleCompra, cantidadTotalDetalle, detalleCompra.getTipoRepuesto())
        }
        
        [compraRepuesto: compra, detalleInfos:detalleInfos]
    }
    
    public class DetalleCompraInfo {

        DetalleCompraRepuesto detalleCompraRepuestoInstance
        Integer cantidadTotalDetalle
        TipoRepuesto tipo

        DetalleCompraInfo(DetalleCompraRepuesto detalleCompraRepuestoInstance, Integer cantidadTotalDetalle, TipoRepuesto tipo) {
            this.detalleCompraRepuestoInstance = detalleCompraRepuestoInstance
            this.cantidadTotalDetalle = cantidadTotalDetalle
            this.tipo = tipo
        }

        String toString(){
            "{$detalleCompraRepuestoInstance.id} -> $tipo.nombre x $cantidadTotalDetalle ($detalleCompraRepuestoInstance.precio)"
        }
     }
}
