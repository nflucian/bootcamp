package ro.neghina.bootcamp.scala.controlflow.okcfp

case class Result(successes: Int, failures: Int) {
  def +:(that: Result) =
    Result(
      this.successes + that.successes,
      this.failures + that.failures)
}

object Result {
  def zero = Result(0, 0)
}
