package ro.neghina.bootcamp.scala.fp

import scala.io.StdIn.readLine
import scala.util.{Random, Try}

/**
 * Functions are:
 *
 * 1. Total - for every input, they return an output
 * 2. Deterministic - for the same input, they return the same output
 * 3. Pure - no side effects, their only effect is computing the return value
 *
 * https://gist.github.com/jdegoes/1b43f43e2d1e845201de853815ab3cb9
 *
 */
object IntroFPApp extends App {
  println("What is your name ?")
  val name = readLine()

  var exec = true
  while (exec) {
    val num = Random.nextInt(5) + 1

    println(s"$name please guess a number from 1 to 5:")

    parseInt(readLine()) match {
      case None => println("You did not enter a number")
      case Some(guess) =>
        if(guess == num)
          println(s"You guessed right, $name !")
        else
          println(s"Bad luck! The number was: $num")
    }


    println(s"Do you want to continue, $name (y/n)?")

    var continue = true
    while (continue) {
      continue = false
      readLine() match {
        case "y" | "Y" => exec = true
        case "n" | "N" => exec = false
        case _ => continue = true
      }
    }

  }

  def parseInt(s: String): Option[Int] = Try(s.toInt).toOption
}
