package ro.neghina.bootcamp.scala.monad

import scala.util.Try

/**
 * Class that implements map and flatMap is a monad
 *
 * Monad = map & flatMap
 */
object MonadApp extends App {

  val possibleName = Some("Lucian")
  val tryMonad = Try("OK")

  class MyMonad[A] private (value: A) {
    def map[B](f: A => B): MyMonad[B] = new MyMonad(f(value))
    def flatMap[B](f: A => MyMonad[B]): MyMonad[B] = f(value)
  }
  object MyMonad {
    def apply[A](value: A): MyMonad[A] = new MyMonad(value)
  }
}
