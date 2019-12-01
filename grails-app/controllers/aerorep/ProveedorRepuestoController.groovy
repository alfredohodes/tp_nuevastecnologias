package aerorep

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProveedorRepuestoController {

    ProveedorRepuestoService proveedorRepuestoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond proveedorRepuestoService.list(params), model:[proveedorRepuestoCount: proveedorRepuestoService.count()]
    }

    def show(Long id) {
        respond proveedorRepuestoService.get(id)
    }

    def create() {
        respond new ProveedorRepuesto(params)
    }

    def save(ProveedorRepuesto proveedorRepuesto) {
        if (proveedorRepuesto == null) {
            notFound()
            return
        }

        try {
            proveedorRepuestoService.save(proveedorRepuesto)
        } catch (ValidationException e) {
            respond proveedorRepuesto.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'proveedorRepuesto.label', default: 'ProveedorRepuesto'), proveedorRepuesto.id])
                redirect proveedorRepuesto
            }
            '*' { respond proveedorRepuesto, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond proveedorRepuestoService.get(id)
    }

    def update(ProveedorRepuesto proveedorRepuesto) {
        if (proveedorRepuesto == null) {
            notFound()
            return
        }

        try {
            proveedorRepuestoService.save(proveedorRepuesto)
        } catch (ValidationException e) {
            respond proveedorRepuesto.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'proveedorRepuesto.label', default: 'ProveedorRepuesto'), proveedorRepuesto.id])
                redirect proveedorRepuesto
            }
            '*'{ respond proveedorRepuesto, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        proveedorRepuestoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'proveedorRepuesto.label', default: 'ProveedorRepuesto'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedorRepuesto.label', default: 'ProveedorRepuesto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
