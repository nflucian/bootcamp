package ro.neghina.bootcamp.spark

import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.sql.SparkSession
import pureconfig.ConfigSource
import pureconfig._
import pureconfig.generic.auto._

import scala.util.{Failure, Success, Try}

trait SparkApp[Context, Result] extends JobRunnable[Context, Result] with Logging {

  def createContext(conf: ConfigSource): Context

  def main(args: Array[String]): Unit = {
    if(args.length < 2) {
      throw new IllegalArgumentException("The first args must be the Job Name and second must be config namespace")
    }

    val appName = args(0)
    val configNamespace = args(1)

    implicit val spark = createSparkSession(appName)

    val output = for {
      context <- Try(createContext(ConfigSource.default.at(configNamespace)))
      result <- Try(run(spark, context))
    } yield result

    output match {
      case Success(_) => log.info(s"$appName successfully run")
      case Failure(e) => log.error(s"$appName failed", e)
    }

  }

  protected def createSparkSession(appName: String): SparkSession = {
    val sparkConfig = new SparkConf()
    val confSpark = sparkConfig.setAppName(appName)
      .setMaster(sparkConfig.get("spark.master", "local[*]"))

    SparkSession.builder().config(confSpark).getOrCreate()
  }

}
