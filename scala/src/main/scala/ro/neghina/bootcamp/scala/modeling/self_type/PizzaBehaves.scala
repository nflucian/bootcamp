package ro.neghina.bootcamp.scala.modeling.self_type

import ro.neghina.bootcamp.scala.modeling.models.{CrustSize, CrustType, Pizza, Topping}

/**
 * TODO:
 */
trait PizzaBehaves {
  this: Pizza =>

  type Money = BigDecimal

  def addTopping(topping: Topping): Pizza = {
    val toppings = this.toppings :+ topping
    this.copy(toppings = toppings)
  }
  def removeTopping(topping: Topping): Pizza = {
    val toppings = this.toppings.filterNot(_.equals(topping))
    this.copy(toppings = toppings)
  }
  def removeAllTopping(): Pizza = this.copy(toppings = Seq())
  def updateCrustSize(cSize: CrustSize): Pizza = this.copy(crustSize = cSize)
  def updateCrustType(cType: CrustType): Pizza = this.copy(crustType = cType)

  def calculatePrice(pricesTopping: Map[Topping, Money],
                     pricesCrustSizePrice: Map[CrustSize, Money],
                     pricesCrustType: Map[CrustType, Money]
                    ): Money = ???
}
