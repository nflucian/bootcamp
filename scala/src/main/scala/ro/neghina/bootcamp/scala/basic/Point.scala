package ro.neghina.bootcamp.scala.basic

class Point(x: Int, y: Int) {
  def this(c: Int) = this(c, 0)
  def this() = this(0)

  def getX = x
  def getY = y

  override def toString: String = s"(${x}, ${y})"
}
