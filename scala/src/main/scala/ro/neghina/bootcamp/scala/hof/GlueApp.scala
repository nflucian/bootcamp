package ro.neghina.bootcamp.scala.hof

object GlueApp extends App {

  def f(a: Int): (Int, String) = (a, s"the f(${a}) result")
  def g(a: Int): (Int, String) = (a, s"the g(${a}) result")

  val (fInt, fString) = f(10)
  val (gInt, gString) = g(fInt)

  // Bind multiple
  val fResult = f(10)
  val gResult = bind(g, fResult)

  def bind(f: Int => (Int, String), acc: (Int, String)): (Int, String) = {
    val (intResult, stringResult) = f(acc._1)
    (intResult, acc._2 + stringResult)
  }

  // Bind for FOR Expressions
  case class Wrapper[A](value: A) {
    def map[B](f: A => B): Wrapper[B] = {
      val result = f(value)
      Wrapper(result)
    }
    def flatMap[B](f: A => Wrapper[B]): Wrapper[B] = f(value)

    override def toString: String = value.toString
  }

  val x = Wrapper(10)
  val y = Wrapper(20)
  println(x.map(_ * 2))

  println {
    for {
      a <- x
      b <- y
    } yield a + b
  }

  println {
    for {
      a <- Wrapper("A")
      b <- Wrapper("B")
    } yield a + b
  }



}
