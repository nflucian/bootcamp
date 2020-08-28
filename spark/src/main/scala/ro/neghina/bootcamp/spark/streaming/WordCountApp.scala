package ro.neghina.bootcamp.spark.streaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{OutputMode, Trigger}

object WordCountApp extends App {
  private val spark = SparkSession.builder()
    .master("local[*]")
    .getOrCreate()
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
