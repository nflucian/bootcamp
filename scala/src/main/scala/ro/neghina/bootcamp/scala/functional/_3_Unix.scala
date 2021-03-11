package ro.neghina.bootcamp.scala.functional

/**
 * who | cut -d' ' -f1 | uniq | wc -l | tr -d ' '
 */
object _3_Unix {

  def who: Seq[String] = ???
  def cut(strings: Seq[String],
          delimiter: String,
          field: Int): Seq[String] = ???
  def uniq(values: Seq[String]): Seq[String] = ???
  def wc(values: Seq[String]): String = ???
  def trim(value: String): String = ???

  val result = trim(wc(uniq(cut(who, " ", 1))))


  def transform[A, B](list: Seq[A], f : A => B): Seq[B] = {
    for { x <- list } yield f(x)
  }

  transform[String, Int](Seq("ok", "test"), s => s.length)

}
