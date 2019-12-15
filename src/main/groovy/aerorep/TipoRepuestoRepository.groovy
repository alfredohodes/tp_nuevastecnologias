package aerorep

class TipoRepuestoRepository {

    TipoRepuestoRepository()
    {

    }

    TipoRepuesto getById(Long id) {
        TipoRepuesto.get(id)
    }
    TipoRepuesto getByCodigo(String codigo) {
        TipoRepuesto.findByCodigo(codigo)
    }
}
