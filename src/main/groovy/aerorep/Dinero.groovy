package aerorep

@groovy.transform.ToString
class Dinero {

    final BigDecimal monto

    Dinero(BigDecimal monto) {

        if (monto < 0) throw new IllegalArgumentException("El monto tiene que ser mayor o igual a 0")
        this.monto = monto
  }
}
