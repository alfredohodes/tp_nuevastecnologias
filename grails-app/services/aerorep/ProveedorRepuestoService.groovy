package aerorep

import grails.gorm.services.Service

@Service(ProveedorRepuesto)
interface ProveedorRepuestoService {

    ProveedorRepuesto get(Serializable id)

    List<ProveedorRepuesto> list(Map args)

    Long count()

    void delete(Serializable id)

    ProveedorRepuesto save(ProveedorRepuesto proveedorRepuesto)

}