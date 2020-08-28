package ro.neghina.bootcamp.scala.basic

import org.scalatest.FlatSpec

class PointSpec extends FlatSpec {

  "empty construct" should "have values zero" in {
      val result = new Point()

      assert(result.getX === 0)
      assert(result.getY === 0)
  }

}
