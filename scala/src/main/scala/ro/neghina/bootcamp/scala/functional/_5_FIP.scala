package ro.neghina.bootcamp.scala.functional

/**
 * Function Input Parameters
 */
object _5_FIP extends App {

  case class Transform[A, B](run: A => B)
  case class Aggregate[A, B](fun: (A, A) => B)

  val stringLength = Transform { s: String => s.length }
  val stringToInt = Transform { s: String => s.toInt }
  println(stringLength.run("bananas"))

  val aggString = Aggregate[Int, Double]((a: Int, b: Int) => a / b)
  val result: Double = aggString.fun(2,3)

}
