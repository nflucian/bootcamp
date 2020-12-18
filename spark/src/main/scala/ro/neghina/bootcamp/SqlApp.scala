package ro.neghina.bootcamp

import org.apache.spark.sql.SparkSession
import pureconfig.ConfigSource
import ro.esolutions.spark.SparkApp
import ro.esolutions.spark.implicits._
import ro.esolutions.spark.io._
import ro.neghina.bootcamp.contexts.SqlContext

object SqlApp extends SparkApp[SqlContext, Unit] {
  override def createContext(conf: ConfigSource): SqlContext = conf.loadOrThrow[SqlContext]

  override def run(implicit spark: SparkSession, context: SqlContext): Unit = {
    context.source.foreach(s => spark.source(s.input).read.createOrReplaceTempView(s.name))
    val df = spark.sql(context.query)
    df.sink(context.output).write
  }
}
