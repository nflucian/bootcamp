package ro.neghina.bootcamp.spark.dataloader

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import ro.neghina.bootcamp.spark.contexts.EntityContext
import ro.neghina.bootcamp.spark.dataloader.MoveApp.MyDataFrame

object AuditHelper {

  implicit class RichDataFrame(df: MyDataFrame) {
    def audit(implicit context: EntityContext): MyDataFrame = {
      df.withColumn(context.entity, current_timestamp())
    }

    def rename(implicit context: EntityContext): DataFrame = {
      context.mapping.foldLeft(df){(acc, c) => acc.withColumnRenamed(c._1, c._2) }
    }
  }

}
