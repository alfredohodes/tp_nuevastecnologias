package aerorep

class DisponibilidadRepuestoController {

    static scaffold = DisponibilidadRepuesto

    def disponibilidadRepuestoRepository

    def show() {
        DisponibilidadRepuesto disponibilidadRepuesto = disponibilidadRepuestoRepository.getById(params.long('id'))
        [disponibilidadRepuesto: disponibilidadRepuesto]
    }
}
