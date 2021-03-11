package ro.neghina.bootcamp.scala.functional

class _4_HOF {

  /**
   * OOP style
   */
  trait StateTaxCalculator {
    def calculate(income: Double): Double
  }
  class AlabamaStateTaxCalculator extends StateTaxCalculator {
    override def calculate(income: Double): Double = ???
  }
  class AlaskaStateTaxCalculator extends StateTaxCalculator {
    override def calculate(income: Double): Double = ???
  }
  class ArizonaStateTaxCalculator extends StateTaxCalculator {
    override def calculate(income: Double): Double = ???
  }


  /**
   * FP style
   */
  def calculateStateTax(f: Double => Double, income: Double): Double = {
    f(income)
  }

  def calculatorAlabamaStateTax(income: Double): Double = ???
  def calculatorAlaskaStateTax(income: Double): Double = ???
  def calculatorArizonaStateTax(income: Double): Double = ???

  val alabama = calculateStateTax(calculatorAlabamaStateTax, 100000)


 case class Price(price: Double) {
   def calculateTax(f: Double => Double): Double = {
     f(price)
   }
 }

  val p = Price(100)
  val tva = p.calculateTax(_ * 0.19)

  def map[A, B](f: (A) => B, list: Seq[A]): Seq[B] = {
    for {
      x <- list
    } yield f(x)
  }

  val values = Seq(1,2,3,4,5)

  map[Int, Int](x => x + 1, values)

}
