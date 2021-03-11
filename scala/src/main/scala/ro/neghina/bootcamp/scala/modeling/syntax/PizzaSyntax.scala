package ro.neghina.bootcamp.scala.modeling.syntax

import ro.neghina.bootcamp.scala.modeling.models.{CrustSize, CrustType, Pizza, Topping}

object PizzaSyntax {
  type Money = BigDecimal

  implicit class PizzaOps(pizza: Pizza) {
    def addTopping(topping: Topping): Pizza = {
      val toppings = pizza.toppings :+ topping
      pizza.copy(toppings = toppings)
    }
    def removeTopping(topping: Topping): Pizza = {
      val toppings = pizza.toppings.filterNot(_.equals(topping))
      pizza.copy(toppings = toppings)
    }
    def removeAllTopping(): Pizza = pizza.copy(toppings = Seq())
    def updateCrustSize(cSize: CrustSize): Pizza = pizza.copy(crustSize = cSize)
    def updateCrustType(cType: CrustType): Pizza = pizza.copy(crustType = cType)

    def calculatePrice(pricesTopping: Map[Topping, Money],
                       pricesCrustSizePrice: Map[CrustSize, Money],
                       pricesCrustType: Map[CrustType, Money]
                      ): Money = ???
  }
}
