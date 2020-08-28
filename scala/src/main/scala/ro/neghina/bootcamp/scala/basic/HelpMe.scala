package ro.neghina.bootcamp.scala.basic

object HelpMe {
  implicit class RichInt(x: Int) {
    def transform(s: String) = s"${s} help ${x}"
  }
}

