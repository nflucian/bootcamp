package ro.neghina.bootcamp.scala.controlflow.okcfp

import scalaz._

case class RawUser(
                    fullName: String,
                    email: String,
                    phone: String,
                    streetAddress: String,
                    city: String,
                    zipCode: String) {
  lazy val person: TransformError \/ Person = {
    fullName.split(" ").toList match {
      case first :: last :: Nil => \/-(Person(first, last))
      case _ => -\/(TransformError(s"Failed parsing first and last name from $fullName"))
    }
  }
}