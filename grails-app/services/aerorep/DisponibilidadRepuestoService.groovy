package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class DisponibilidadRepuestoService {

    def disponibilidadRepuestoRepository
    def notificacionService

    def getDisponibilidadesLibresPorTipoOrdenadasPorVencimiento(TipoRepuesto tipo) {
        disponibilidadRepuestoRepository.getAllByTipoOrdenadosPorVencimientoAsc(tipo).findAll { disp ->
            !disp.estaVencido() && disp.getCantidadDisponible() > 0
        }
    }

    def reservar(Long disponibilidadId, Integer cantReserva) {
        DisponibilidadRepuesto disponibilidad = disponibilidadRepuestoRepository.getById(disponibilidadId)
        disponibilidad.reservar(cantReserva)
        disponibilidad.save()

        // Notifica stock mínimo
        Integer cantidadAlertaStockMinimo = disponibilidad.tipo.cantidadAlertaStockMinimo
        Integer cantidadLibrePostReserva = getCantidadDisponibleLibrePorTipo(disponibilidad.tipo)
        if(cantidadLibrePostReserva <= cantidadAlertaStockMinimo && (cantidadLibrePostReserva + cantReserva) > cantidadAlertaStockMinimo) {
            notificacionService.notificar("Alerta de stock minimo. Repuesto $disponibilidad.tipo.nombre tiene stock $cantidadLibrePostReserva. Se encuentra por debajo del minimo ($cantidadAlertaStockMinimo unidades)")
        }
    }

    Dinero getPrecioPorUnidad(Long disponibilidadId)
    {
        DetalleCompraRepuesto detalleCompra = DetalleCompraRepuesto.createCriteria().get{
            repuestos{
                eq('id', disponibilidadId)
            }
        }
        detalleCompra.getPrecioPorUnidad()
    }

    Integer getCantidadDisponibleLibrePorTipo(TipoRepuesto tipo) {
        getDisponibilidadesLibresPorTipoOrdenadasPorVencimiento(tipo).sum(0) { disponibilidad -> 
            disponibilidad.getCantidadDisponible()
        }
    }
    
}
