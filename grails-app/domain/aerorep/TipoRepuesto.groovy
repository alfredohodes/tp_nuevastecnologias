package aerorep

class TipoRepuesto {

    String name
    Integer cantidadAlertaStockMinimo

    static hasMany = [repuestos: Repuesto]
    
    static constraints = {
        name(nullable: false, blank: false)
    }

    void reservarParaOT(Integer cantidad, OrdenDeTrabajo ot) {

        // TODO: Buscar los [cantidad] repuestos disponibles más próximos a vencer y reservarlos para esta OT

        // TODO: Si no alcanzan -> Exception ("No hay repuestos disponibles suficiente")

        // TODO: Si, después de reservar, la cantidad de repuestos disponible es <= cantidadAlertaStockMinimo -> Enviar alerta

        // NOTA: Ver si estas funcionalidades van acá o en un servicio
    }
}
