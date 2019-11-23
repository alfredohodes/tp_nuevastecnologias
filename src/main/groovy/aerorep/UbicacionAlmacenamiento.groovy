package aerorep

@groovy.transform.ToString
class UbicacionAlmacenamiento {

    String deposito
    String zona
    String estanteria
    String espacio

    UbicacionAlmacenamiento(String deposito, String zona, String estanteria, String espacio) {
        this.deposito   = deposito
        this.zona       = zona
        this.estanteria = estanteria
        this.espacio    = espacio
    }
}
