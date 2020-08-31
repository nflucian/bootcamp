package ro.neghina.bootcamp

import org.apache.spark.sql.SparkSession
import pureconfig.ConfigSource
import ro.neghina.bootcamp.contexts.MyContext
import pureconfig._
import pureconfig.generic.auto._
import ro.esolutions.spark.SparkApp
import ro.esolutions.spark.implicits._
import ro.esolutions.spark.io._

object MyApp extends SparkApp[MyContext, Unit ]{
  override def createContext(conf: ConfigSource): MyContext = conf.loadOrThrow[MyContext]

  override def run(implicit spark: SparkSession, context: MyContext): Unit = {
    val df = spark.source(context.input).read
    df.sink(context.output).write
  }
}
