package aerorep

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProveedorRepuestoSpec extends Specification implements DomainUnitTest<ProveedorRepuesto> {

    def setup() {
    }

    def cleanup() {
    }

    void "test persistencia"() {

        given: "un proveedor"
        new ProveedorRepuesto(cuit:"11-11111111-1", razonSocial:"Proveedor 1 SRL").save()

        expect:"cantidad 1"
            ProveedorRepuesto.findAll().size() == 1
    }
}
