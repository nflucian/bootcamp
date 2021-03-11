package ro.neghina.bootcamp.scala.functional

import org.scalatest.FlatSpec

/**
 * Function Input Parameter
 */
class FIPSpec extends FlatSpec {

  case class Transform[A, B](run: A => B)
  case class Aggregate[A, B](exec: (A, A) => B)

  it should "Transform" in {
    val stringLength = Transform { s: String => s.length }
    val stringToInt = Transform { s: String => s.toInt }

    val resultLength = stringLength.run("bananas")
    val resultCast = stringToInt.run("10")

    assert( resultLength == 7 )
    assert( resultCast === 10 )
  }

  it should "Aggregate" in {
    val plusStringLength = Aggregate { (a: String, b: String) => a.length + b.length}

    val response = plusStringLength.exec("foo", "bar")
    assert( response == 6)
  }

}
