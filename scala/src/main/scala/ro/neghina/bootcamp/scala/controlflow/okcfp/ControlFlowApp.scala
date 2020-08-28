package ro.neghina.bootcamp.scala.controlflow.okcfp

import scalaz._
import scalaz.Scalaz._

object ControlFlowApp extends App {

    val emitRecords: () => Seq[RawUser] = Source.emit(Config.current)

    val result: Result = go(emitRecords)

    // side-effect print to see results
    println(result.toString)


  private def go(emit: () => Seq[RawUser]): Result = {
    emit()
      .map(transform)
      .map(toResult)
      .foldLeft(Result.zero)(_ +: _)
  }

  private def toResult(v: \/[TransformError, DomainUser]): Result = {
    v match {
      case \/-(_) => Result(1, 0)
      case -\/(e) => {
        println(s"Transform Error: ${e.error}")
        Result(0, 1)
      }
    }
  }

  private def transform(r: RawUser): \/[TransformError, DomainUser] = {
    val maybePerson: TransformError \/ Person = r.person
    val maybePhone: TransformError \/ PhoneNumber = PhoneNumber.from(r.phone)

    (maybePerson |@| maybePhone)(DomainUser)
  }
}

