package aerorep

//@groovy.transform.ToString
class ProveedorRepuesto {

    String cuit
    String razonSocial

    static constraints = {
    }

    String toString(){
       "{$id} -> " + razonSocial
    }
}
