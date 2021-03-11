package ro.neghina.bootcamp.scala.modeling.companion

import ro.neghina.bootcamp.scala.modeling.models.{Cheese, LargeCrustSize, MediumCrustSize, Onions, RegularCrustType}

object AppCompanion extends App {
  import Pizza._

  val pizza: Pizza = Pizza(RegularCrustType, MediumCrustSize, Seq(Cheese))

  val pizza2 = addTopping(pizza, Onions)
  val pizza3 = updateCrustSize(pizza2, LargeCrustSize)
}
