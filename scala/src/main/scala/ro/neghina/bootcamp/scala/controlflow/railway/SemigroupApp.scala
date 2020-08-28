package ro.neghina.bootcamp.scala.controlflow.railway

import scalaz.Scalaz._

object SemigroupApp extends App {
  val o1: Option[Int] = Some(100)
  val o2: Option[Int] = None
  val o3: Option[Int] = Some(10)

  println(o1 |+| o2)
  println(o1 |+| o3)
}
