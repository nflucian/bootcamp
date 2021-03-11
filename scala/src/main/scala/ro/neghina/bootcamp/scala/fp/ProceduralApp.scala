package ro.neghina.bootcamp.scala.fp

import scala.io.StdIn.readLine
import scala.util.Random

object ProceduralApp extends App {

  println("What is your name ?")
  val name = readLine()

  var exec = true
  while (exec) {
    val num = Random.nextInt(5) + 1

    println(s"$name please guess a number from 1 to 5:")
    val guess = readLine().toInt

    if(guess == num)
      println(s"You guessed right, $name !")
    else
      println(s"Bad luck! The number was: $num")

    println(s"Do you want to continue, $name (y/n)?")

    readLine() match {
      case "y" | "Y" => exec = true
      case "n" | "N" => exec = false
    }
  }

}
