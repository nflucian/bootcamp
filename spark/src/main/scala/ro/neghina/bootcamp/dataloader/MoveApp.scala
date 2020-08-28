package ro.neghina.bootcamp.dataloader

import org.apache.spark.sql.{DataFrame, SparkSession}
import pureconfig.ConfigSource
import org.apache.spark.sql.functions._
import pureconfig._
import pureconfig.generic.auto._
import ro.neghina.bootcamp.contexts.EntityContext
import ro.neghina.spark.implicits._
import ro.neghina.spark.SparkApp

object MoveApp extends SparkApp[EntityContext, Unit] {
  type MyDataFrame = DataFrame

  override def createContext(conf: ConfigSource): EntityContext = conf.loadOrThrow[EntityContext]

  override def run(implicit spark: SparkSession, context: EntityContext): Unit = {
    import spark.implicits._

    val df: MyDataFrame = Seq(
      (1, "lucian", 40),
      (2, "bogdanel", 35)
    ).toDF("id", "name", "age")

    val result: DataFrame = df.transform(moveTrasform)

    result.show(false)
  }

  def moveTrasform(df: DataFrame)(implicit context: EntityContext): DataFrame = {
    df.audit.rename(context)
  }


}