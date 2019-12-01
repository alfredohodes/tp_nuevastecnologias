package aerorep

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProveedorRepuestoServiceSpec extends Specification {

    ProveedorRepuestoService proveedorRepuestoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProveedorRepuesto(...).save(flush: true, failOnError: true)
        //new ProveedorRepuesto(...).save(flush: true, failOnError: true)
        //ProveedorRepuesto proveedorRepuesto = new ProveedorRepuesto(...).save(flush: true, failOnError: true)
        //new ProveedorRepuesto(...).save(flush: true, failOnError: true)
        //new ProveedorRepuesto(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //proveedorRepuesto.id
    }

    void "test get"() {
        setupData()

        expect:
        proveedorRepuestoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProveedorRepuesto> proveedorRepuestoList = proveedorRepuestoService.list(max: 2, offset: 2)

        then:
        proveedorRepuestoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        proveedorRepuestoService.count() == 5
    }

    void "test delete"() {
        Long proveedorRepuestoId = setupData()

        expect:
        proveedorRepuestoService.count() == 5

        when:
        proveedorRepuestoService.delete(proveedorRepuestoId)
        sessionFactory.currentSession.flush()

        then:
        proveedorRepuestoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProveedorRepuesto proveedorRepuesto = new ProveedorRepuesto()
        proveedorRepuestoService.save(proveedorRepuesto)

        then:
        proveedorRepuesto.id != null
    }
}
