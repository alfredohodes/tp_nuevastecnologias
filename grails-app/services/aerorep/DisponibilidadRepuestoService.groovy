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
        disponibilidad.save(flush:true)

        // Notifica stock minimo
        Integer cantidadAlertaStockMinimo = disponibilidad.tipo.cantidadAlertaStockMinimo
        Integer cantidadLibrePostReserva = getCantidadDisponibleLibrePorTipo(disponibilidad.tipo)
        println "cantidadAlertaStockMinimo $cantidadAlertaStockMinimo - cantidadLibrePostReserva $cantidadLibrePostReserva"
        if(cantidadLibrePostReserva <= cantidadAlertaStockMinimo && (cantidadLibrePostReserva + cantReserva) > cantidadAlertaStockMinimo) {
            notificacionService.notificar("Compre, compre")
        }
    }

    Dinero getPrecioPorUnidad(Long disponibilidadId)
    {
        DetalleCompraRepuesto detalleCompra = DetalleCompraRepuesto.createCriteria().get{
            repuestos{
                eq('id', disponibilidadId)
            }
        }
        println "detalleCompra $detalleCompra"
        detalleCompra.getPrecioPorUnidad()
    }

    Integer getCantidadDisponibleLibrePorTipo(TipoRepuesto tipo) {
        getDisponibilidadesLibresPorTipoOrdenadasPorVencimiento(tipo).sum(0) { disponibilidad -> 
            disponibilidad.getCantidadDisponible()
        }
    }
    
}
