# Algebraic Data Types

```scala

sealed trait Status extends Product with Seializable

object Status {

	case object Ok extend Status
	case object Nok extend Status
}
