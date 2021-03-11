package ro.neghina.bootcamp.scala.modeling.syntax

import ro.neghina.bootcamp.scala.modeling.models.{Cheese, LargeCrustSize, MediumCrustSize, Onions, Pizza, RegularCrustType}

object AppSyntax extends App {
  import PizzaSyntax._
  import LucianSyntax._

  val pizza = Pizza(RegularCrustType, MediumCrustSize, Seq(Cheese))

  val result = pizza.addTopping(Onions)
    .updateCrustSize(LargeCrustSize)
    .thin()
}
