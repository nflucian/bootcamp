package ro.neghina.spark

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.current_timestamp
import ro.neghina.bootcamp.contexts.EntityContext
import ro.neghina.bootcamp.dataloader.MoveApp.MyDataFrame

package object implicits {
  implicit class RichDataFrame(df: MyDataFrame) {
    def audit(implicit context: EntityContext): MyDataFrame = {
      df.withColumn(context.entity, current_timestamp())
    }

    def rename(implicit context: EntityContext): DataFrame = {
      context.mapping.foldLeft(df){(acc, c) => acc.withColumnRenamed(c._1, c._2) }
    }
  }
}
