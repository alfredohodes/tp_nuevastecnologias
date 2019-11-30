package aerorep

class TipoRepuesto {

    String nombre
    String codigo
    Integer cantidadAlertaStockMinimo
    
    static constraints = {
        nombre(nullable: false, blank: false)
        codigo(nullable: false, blank: false, unique: true)
    }

}
