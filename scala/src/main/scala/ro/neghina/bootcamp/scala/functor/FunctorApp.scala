package ro.neghina.bootcamp.scala.functor

/**
 * Class that implements map is a functor
 *
 * Functor = map-able
 */
object FunctorApp {

  trait MyFunctor[A] {
    def map[B](f : A => B): MyFunctor[B]
  }

}
