package aerorep

class DisponibilidadRepuesto {

    TipoRepuesto tipo

    UbicacionAlmacenamiento ubicacion
    static embedded = ['ubicacion']
    
    Integer cantidad

    String numeroDeSerie
    String lote
    
    Date vencimiento

    static constraints = {
        tipo nullable: false
        cantidad min: 1
    }

    boolean estaVencido() {
        // TODO: Usar un bean de calendario
        false
    }

}
