package ro.neghina.bootcamp.scala.modeling.modules

import ro.neghina.bootcamp.scala.modeling.models.{Cheese, LargeCrustSize, MediumCrustSize, Onions, Pizza, RegularCrustType}

object AppModules extends App {
  object PizzaServiceModule extends PizzaServiceInterface
  import PizzaServiceModule._

  val pizza: Pizza = Pizza(RegularCrustType, MediumCrustSize, Seq(Cheese))
  val pizza2 = addTopping(pizza, Onions)
  val pizza3 = updateCrustSize(pizza2, LargeCrustSize)
}
