package ro.neghina.bootcamp.scala.modeling.modules

import ro.neghina.bootcamp.scala.modeling.models.{CrustSize, CrustType, Pizza, Topping}

trait PizzaServiceInterface {
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
