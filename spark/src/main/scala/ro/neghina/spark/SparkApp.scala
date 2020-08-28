package ro.neghina.spark

import java.net.URL

import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.sql.SparkSession
import pureconfig.ConfigSource
import scopt.OParser

import scala.util.{Failure, Success, Try}

trait SparkApp[Context, Result] extends JobRunnable[Context, Result] with ConfigBuilder with Logging {

  def createContext(conf: ConfigSource): Context

  def main(implicit args: Array[String]): Unit = {

    extractAppArgs match {
      case Some(appArgs) => {
        implicit val spark = createSparkSession(appArgs.appName)
        implicit val conf = appConfiguration(appArgs)

        val output = for {
          context <- Try(createContext(conf))
          result <- Try(run(spark, context))
        } yield result

        output match {
          case Success(_) => log.info(s"${appArgs.appName} successfully run")
          case Failure(e) => log.error(s"${appArgs.appName} failed", e)
        }
      }
      case _ =>
    }
  }

  protected def extractAppArgs(implicit args: Array[String]): Option[AppArgs] = {
    val builder = OParser.builder[AppArgs]
    val parser = {
      import builder._
      OParser.sequence(
        programName("spark-submit ... <application-jar> "),
        note("Available options:"),
        opt[String]('j', "job")
          .required()
          .action((v, cfg) => cfg.copy(appName = v))
          .text("job is a required application name property"),
        opt[String]('n', "namespace")
          .optional()
          .action((v, cfg) => cfg.copy(namespace = Some(v)))
          .text("optional configuration namespace property"),
        opt[String]('u', "url")
          .optional()
          .action((v, cfg) => cfg.copy(url = Try(new URL(v)).toOption))
          .text("optional config url property"),
        opt[String]('l', "literal")
          .optional()
          .action((v, cfg) => cfg.copy(literalConf = Some(v)))
          .text("optional literal config property"),
        help("help").text("prints this usage text")
      )
    }

    OParser.parse(parser, args, AppArgs())
  }

  protected def createSparkSession(appName: String): SparkSession = {
    val sparkConfig = new SparkConf()
    val confSpark = sparkConfig.setAppName(appName)
      .setMaster(sparkConfig.get("spark.master", "local[*]"))

    SparkSession.builder().config(confSpark).getOrCreate()
  }

}
