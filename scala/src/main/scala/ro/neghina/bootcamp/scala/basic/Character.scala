package ro.neghina.bootcamp.scala.basic

sealed trait Person {
  def name: String
}

final case class Character(name: String, isThief: Boolean) extends Person
final case class RockStar(name: String) extends Person

object Person {
  def fromString(str: String): Person = str match {
    case "rocker" => RockStar(str)
    case _ => Character(str, false)
  }

  def toString(p: Person) = p match {
    case RockStar(_) => "I'm a rock star"
    case Character("lucian", _) => s"I love big data"
    case Character(n, _) => s"hello ${n}"
    case _ => "nu pot"
  }
}