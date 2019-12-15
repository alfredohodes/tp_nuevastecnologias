package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class DisponibilidadRepuestoService {

    def disponibilidadRepuestoRepository

    def getDisponibilidadesLibresPorTipoOrdenadasPorVencimiento(TipoRepuesto tipo) {
        disponibilidadRepuestoRepository.getAllByTipoOrdenadosPorVencimientoAsc(tipo).findAll { disp ->
            !disp.estaVencido() && disp.cantidadReservada < disp.cantidad
        }
    }

    def reservar(Long disponibilidadId, Integer cantAReservar) {
        //TODO: Chequear cant. vs mínimo y enviar alerta
        DisponibilidadRepuesto disponibilidad = disponibilidadRepuestoRepository.getById(disponibilidadId)
        disponibilidad.reservar(cantAReservar)
        disponibilidad.save(flush:true)
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


            

}
