package ro.neghina.bootcamp.scala.controlflow.railway

import scalaz.Scalaz._
import scalaz._

object DisjunctionApp extends App {

  val v1: \/[String, Int] = \/-(100)
  val v2: \/[String, Int] = -\/("fail")


  println(v1 |+| v2)
  println(v1.map(x => x + 10))
  println(v2.map(x => x + 10))

}
