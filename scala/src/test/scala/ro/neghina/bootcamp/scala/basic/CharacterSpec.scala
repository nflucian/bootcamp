package ro.neghina.bootcamp.scala.basic

import org.scalatest.FlatSpec

class CharacterSpec extends FlatSpec {

  it should "works" in {
    val h = Character("Hadrian", true)
    val r = h.copy(name = "Royce")
    println(h)
    println(r)

    println(r.name)
  }

}
