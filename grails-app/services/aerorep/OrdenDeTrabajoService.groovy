package aerorep

import grails.gorm.transactions.Transactional

@Transactional
class OrdenDeTrabajoService {
    
    def disponibilidadRepuestoService

    /*
        Reserva todos los repuestos requeridos para esta OT
    */
    def prepararOT(Long id) {
        OrdenDeTrabajo ot = OrdenDeTrabajo.get(id)
        ot.requerimientoRepuestos.each { requerimiento ->

            def disponibilidadesNoVencidasConCantidadesNoReservadas = disponibilidadRepuestoService.getDisponibilidadesLibresPorTipoOrdenadasPorVencimiento(requerimiento.tipo)
            
            // Inject para ir reservando repuestos disponibles ordenados por vencimiento. Acumulo la cant. que resta reservar
            disponibilidadesNoVencidasConCantidadesNoReservadas.inject(requerimiento.getCantidadPendienteAReservar()) { cantPendiente, disponibilidad ->
                //println "cantLibreEnDispActual: ${disponibilidad.getCantidadDisponible()} || cantPendiente: $cantPendiente"
                def cantAReservar = [disponibilidad.getCantidadDisponible(), cantPendiente].min()
                //println "disp: $disponibilidad || Cant: ${disponibilidad.getCantidadDisponible()}/$disponibilidad.cantidad || Pendiente: $cantPendiente || A reservar: $cantAReservar"
                
                if(cantAReservar > 0)
                {
                    disponibilidadRepuestoService.reservar(disponibilidad.id, cantAReservar)
                    requerimiento.agregarReservaRepuesto(new ReservaRepuesto(cantidad:cantAReservar, disponibilidadRepuesto:disponibilidad))
                }
                cantPendiente-cantAReservar
            }
        }
        ot.save(flush:true)
    }

    /*
        El valor de una OT se calcula como el costo total de los repuestos más un porcentaje de ganancia sobre este monto
    */
    Dinero calcularValorOT(Long otId, Float porcentajeGanancia)
    {
        OrdenDeTrabajo ot = OrdenDeTrabajo.get(otId)
        Dinero costoRepuestos = ot.requerimientoRepuestos.sum { requerimiento -> 
            requerimiento.reservasRepuestos.sum { reserva -> 
                Dinero precioRepuestoPorUnidad = disponibilidadRepuestoService.getPrecioPorUnidad(reserva.disponibilidadRepuesto.id)
                precioRepuestoPorUnidad * reserva.cantidad
            }
        }
        costoRepuestos * (1 + porcentajeGanancia / 100)
    }
}
