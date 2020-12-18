package ro.neghina.bootcamp.scala.functional

/**
 * Output depends only on input.
 * No side effects
 *
 * Benefits
 * - easier to reason about
 * - easier to combine
 * - easier to test
 * - easier to debug
 * - easier to parallelize
 *
 **/
object _1_PureFunctions {

  // pure function signature tell all
  def foo(s: String): Int = ???
  def foo(persons: Seq[String], n: Int): String = ???

  //  with side effect
  import cats.effect.IO
  def getUsername(): IO[String] = ???
  def save(out: String): IO[Unit] = ???


}
