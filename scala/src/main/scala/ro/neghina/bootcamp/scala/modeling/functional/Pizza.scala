package ro.neghina.bootcamp.scala.modeling.functional

import ro.neghina.bootcamp.scala.modeling.models.{CrustSize, CrustType, Topping}

case class Pizza(crustType: CrustType, crustSize: CrustSize, toppings: Seq[Topping]) {
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
}