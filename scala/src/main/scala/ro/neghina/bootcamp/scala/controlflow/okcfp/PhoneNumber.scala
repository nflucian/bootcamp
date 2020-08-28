package ro.neghina.bootcamp.scala.controlflow.okcfp

import scalaz._
import scalaz.Scalaz._
import scala.util.Try

case class PhoneNumber(
                        countryCode: Int,
                        areaCode: Int,
                        prefix: Int,
                        lineNumber: Int) {
  override def toString = s"$countryCode ($areaCode) $prefix-$lineNumber"
}

object PhoneNumber {
  private val pattern = """(\d{1})-(\d{3})-(\d{3})-(\d{4})""".r

  private def toInt(s: String): TransformError \/ Int =
    Try(s.toInt).toDisjunction.leftMap(e => TransformError(e.getMessage))

  def from(phoneString: String): TransformError \/ PhoneNumber = {
    phoneString match {
      case pattern(code, area, prefix, line) =>
        (toInt(code) |@| toInt(area) |@| toInt(prefix) |@| toInt(line))(PhoneNumber.apply _)
      //\/-(PhoneNumber(code.toInt, area.toInt, prefix.toInt, line.toInt))
      case _ => -\/(TransformError(s"$phoneString didn't match expected phone number pattern"))
    }
  }
}