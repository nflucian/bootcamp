package ro.neghina.bootcamp.scala.modeling.functional

import ro.neghina.bootcamp.scala.modeling.models.{Cheese, LargeCrustSize, MediumCrustSize, Onions, RegularCrustType}

object AppFunctionalObj extends App {
  val pizza = Pizza(RegularCrustType, MediumCrustSize, Seq(Cheese))

  val result = pizza.addTopping(Onions)
    .updateCrustSize(LargeCrustSize)
}
