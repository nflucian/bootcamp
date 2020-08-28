package ro.neghina.bootcamp.streaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import pureconfig.ConfigSource
import ro.neghina.spark.SparkApp

object WordCountApp extends SparkApp[Unit, Unit] {

  override def createContext(conf: ConfigSource): Unit = Unit

  override def run(implicit spark: SparkSession, context: Unit): Unit = {
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val lines = spark
      .readStream.format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    val groupedByWords = lines
      .select(explode(split(col("value"), "\\s")).as("word"))
      .groupBy("word")
      //    .groupByKey(raw => raw.getAs[String]("id")).mapGroupsWithState()
      .count()

    groupedByWords
      .writeStream
      .format("console")
      .outputMode(OutputMode.Complete)
      .trigger(Trigger.ProcessingTime("1 second"))
      .option("checkpointLocation", "checkpoint") // checkpoint = "hdfs://ns1/user/systest/checkpoint"
      .start().awaitTermination()
  }
}
