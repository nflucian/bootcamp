package ro.neghina.bootcamp.scala.modeling.models

sealed trait CrustType

case object RegularCrustType extends CrustType
case object ThinCrustType extends CrustType
case object ThickCrustType extends CrustType
