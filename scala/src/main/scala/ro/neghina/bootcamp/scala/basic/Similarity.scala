package ro.neghina.bootcamp.scala.basic

trait Similarity {
  val name: String
  def isSimilar(x: Any): Boolean
  def isNotSimilar(x: Any): Boolean = !isSimilar(x)
}