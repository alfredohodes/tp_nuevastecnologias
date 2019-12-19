package aerorep

//@groovy.transform.ToString
class DisponibilidadRepuesto {

    TipoRepuesto tipo

    UbicacionAlmacenamiento ubicacion
    static embedded = ['ubicacion']
    
    Integer cantidad
    Integer cantidadReservada = 0

    String numeroDeSerie
    String lote
    
    Date vencimiento

    static constraints = {
        tipo nullable: false
        cantidad min: 1
    }

    void reservar(Integer cantidadAReservar)
    {
        if(cantidadAReservar > getCantidadDisponible()) throw new IllegalArgumentException("intentando reservar más cantidad que lo disponible")
        setCantidadReservada(cantidadReservada + cantidadAReservar)
    }

    void liberar(Integer cantidadALiberar)
    {
        if(cantidadALiberar > cantidadReservada()) throw new IllegalArgumentException("intentando liberar más cantidad que lo reservado")
        setCantidadReservada(cantidadReservada - cantidadALiberar)
    }

    boolean estaVencido() {
        vencimiento < new Date()
    }

    Integer getCantidadDisponible()
    {
        cantidad - cantidadReservada
    }

    String toString(){
        "{$id} -> $tipo.nombre x $cantidad (${getCantidadDisponible()} libres) - Vto: ${vencimiento.format('dd/MM/yyyy')}"
    }

}
