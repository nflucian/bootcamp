package ro.neghina.bootcamp.scala.basic


case class Wrapper(i: Int)
case class Zero()

object HasGetApp extends App {

  trait HasGet[A] {
    def get(a: A): Int
  }

  implicit val wrap: HasGet[Wrapper] = new HasGet[Wrapper] {
    def get(a: Wrapper) = a.i
  }

  def add1[A](a: A)(implicit hga: HasGet[A]): Int = hga.get(a) + 1


  println( add1(Zero())(new HasGet[Zero] {
    override def get(a: Zero): Int = 0
  } ) )

}