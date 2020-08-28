package ro.neghina.bootcamp.scala.controlflow

package object okcfp {

  case class Person(firstName: String, lastName: String)
  case class TransformError(error: String)
  case class DomainUser(person: Person, phoneNumber: PhoneNumber)

}
