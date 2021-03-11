package ro.neghina.bootcamp.scala.modeling.models

sealed trait CrustSize

case object SmallCrustSize extends CrustSize
case object MediumCrustSize extends CrustSize
case object LargeCrustSize extends CrustSize
