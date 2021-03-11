package ro.neghina.bootcamp.scala.modeling.models

case class Pizza(crustType: CrustType, crustSize: CrustSize, toppings: Seq[Topping])
case class Order(pizzas: Seq[Pizza], customer: Customer)
case class Customer(name: String, phone: String)
