package ro.neghina.bootcamp.scala.basic

object Helpers {
  implicit class RichInt(x: Int) {
    def transform(s: String) = s + x
  }
}

