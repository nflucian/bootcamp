package ro.neghina.bootcamp.scala.modeling.companion

import ro.neghina.bootcamp.scala.modeling.models.{CrustSize, CrustType, Topping}

case class Pizza(crustType: CrustType, crustSize: CrustSize, toppings: Seq[Topping])

object Pizza {
  type Money = BigDecimal

  def addTopping(pizza: Pizza, topping: Topping): Pizza = ???
  def removeTopping(pizza: Pizza, topping: Topping): Pizza = ???
  def removeAllTopping(pizza: Pizza): Pizza = ???

  def updateCrustSize(pizza: Pizza, cSize: CrustSize): Pizza = ???
  def updateCrustType(pizza: Pizza, cType: CrustType): Pizza = ???

  def calculatePrice(p: Pizza,
                     pricesTopping: Map[Topping, Money],
                     pricesCrustSizePrice: Map[CrustSize, Money],
                     pricesCrustType: Map[CrustType, Money]
                    ): Money = ???
}
