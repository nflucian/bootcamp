package ro.neghina.bootcamp.scala.modeling.oop

import ro.neghina.bootcamp.scala.modeling.models.{Cheese, LargeCrustSize, MediumCrustSize, Onions, Pizza, RegularCrustType}

object AppServices extends App {
  import PizzaService._

  val pizza: Pizza = Pizza(RegularCrustType, MediumCrustSize, Seq(Cheese))

  val pizza2 = addTopping(pizza, Onions)
  val pizza3 = updateCrustSize(pizza2, LargeCrustSize)
}
