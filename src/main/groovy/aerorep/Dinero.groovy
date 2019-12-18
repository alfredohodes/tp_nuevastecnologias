package aerorep

@groovy.transform.ToString
class Dinero {

    BigDecimal monto

    Dinero() {
      this.monto = 0
    }

    Dinero(BigDecimal monto) {

        if (monto < 0) throw new IllegalArgumentException("El monto tiene que ser mayor o igual a 0")
        this.monto = monto
  }

  def plus(Dinero otro) {
      new Dinero(this.monto + otro.monto)
  }

  def div(Number divNbr) {
      new Dinero(this.monto / divNbr)
  }

  def multiply(Number multNbr) {
    new Dinero(this.monto * multNbr)
  }

  def multiply(Integer multInt) {
    new Dinero(this.monto * multInt)
  }

    String toString(){
        "ARS ${String.format("%.2f", monto)}"
    }
}
