package ro.neghina.bootcamp.scala.modeling.syntax

import ro.neghina.bootcamp.scala.modeling.models.{Pizza, ThinCrustType, Topping}

object LucianSyntax {

  implicit class PizzaLucian(pizza: Pizza) {
    def thin(): Pizza = {
      pizza.copy(crustType = ThinCrustType)
    }
  }
}
