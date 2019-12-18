package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class OrdenDeTrabajoService {
    
    def disponibilidadRepuestoService
    def ordenDeTrabajoRepository

    /*
        Reserva todos los repuestos requeridos para esta OT
    */
    def prepararOT(Long otId) {
        OrdenDeTrabajo ot = getOrdenDeTrabajoById(otId)
        ot.requerimientoRepuestos.each { requerimiento ->

            def disponibilidadesNoVencidasConCantidadesNoReservadas = disponibilidadRepuestoService.getDisponibilidadesLibresPorTipoOrdenadasPorVencimiento(requerimiento.tipo)
            
            // Inject para ir reservando repuestos disponibles ordenados por vencimiento. Acumulo la cant. que resta reservar
            def cantPendienteAReservar = requerimiento.getCantidadPendienteAReservar()
            disponibilidadesNoVencidasConCantidadesNoReservadas.inject(cantPendienteAReservar) { cantPendiente, disponibilidad ->
                def cantAReservar = [disponibilidad.getCantidadDisponible(), cantPendiente].min()
                
                if(cantAReservar > 0)
                {
                    disponibilidadRepuestoService.reservar(disponibilidad.id, cantAReservar)
                    requerimiento.agregarReservaRepuesto(new ReservaRepuesto(cantidad:cantAReservar, disponibilidadRepuesto:disponibilidad))
                    def resultadoDeDispSave = disponibilidad.save(failOnError:true, flush:true)
                }

                cantPendiente-cantAReservar
            }
        }
        ot.save(flush:true)
    }

    /*
        Ejecuta la OT y devuelve una lista con las ubicaciones de los repuestos reservados
    */
    def ejecutarOT(Long otId) {
        OrdenDeTrabajo ot = getOrdenDeTrabajoById(otId)
        ot.ejecutar()
        ot.save(flush:true)

        getReservasRepuestosParaOT(otId)
    }

    /*
        El valor de una OT se calcula como el costo total de los repuestos más un porcentaje de ganancia sobre este monto
    */
    Dinero calcularValorOT(Long otId, Float porcentajeGanancia)
    {
        OrdenDeTrabajo ot = getOrdenDeTrabajoById(otId)
        Dinero costoRepuestos = ot.requerimientoRepuestos.sum(new Dinero()) { requerimiento -> 
            requerimiento.reservasRepuestos.sum(new Dinero()) { reserva -> 
                Dinero precioRepuestoPorUnidad = disponibilidadRepuestoService.getPrecioPorUnidad(reserva.disponibilidadRepuesto.id)
                precioRepuestoPorUnidad * reserva.cantidad
            }
        }
        costoRepuestos * (1 + porcentajeGanancia / 100)
    }

    def getReservasRepuestosParaOT(Long otId) {
        OrdenDeTrabajo ot = getOrdenDeTrabajoById(otId)
        ot.requerimientoRepuestos.sum { requerimiento -> requerimiento.reservasRepuestos}
    }

    def getAllOrdenesDeTrabajo() {
        ordenDeTrabajoRepository.getAll()
    }

    def getOrdenDeTrabajoById(Long otId) {
        ordenDeTrabajoRepository.getById(otId)
    }
}
