package aerorep

class DisponibilidadRepuestoRepository {

    DisponibilidadRepuestoRepository() {}

    
    DisponibilidadRepuesto getById(Long id) {
        DisponibilidadRepuesto.get(id)
    }

    def getAllByTipoOrdenadosPorVencimientoAsc(TipoRepuesto tipo) {
        DisponibilidadRepuesto.findAllByTipo(tipo, [sort: "vencimiento", order: "asc"])
    }
}
