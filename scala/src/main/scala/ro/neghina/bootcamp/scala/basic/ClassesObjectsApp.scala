package ro.neghina.bootcamp.scala.basic

object ClassesObjectsApp extends App {
  def factorOf(x: Int)(implicit y: Int) = x % y == 0

  implicit val t: Int = 3

  println(factorOf(4))
}
