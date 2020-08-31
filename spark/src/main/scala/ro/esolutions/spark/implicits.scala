package ro.esolutions.spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import ro.esolutions.spark.io.sinks.DataFrameSink
import ro.esolutions.spark.io._

package object implicits {
  /** DataFrame decorator. */
  implicit class RichDataFrame(df: DataFrame) {
    def sink[SC <: DataSinkConfiguration](configuration: SC)(implicit sinkFactory: DataFrameSinkFactory): DataFrameSink[SC, DataFrame] =
      sinkFactory.apply[SC, DataFrame](configuration, df)
  }

  /** SparkSession decorator. */
  implicit class SparkSessionOps(spark: SparkSession) {
    def source[SC <: DataSourceConfiguration](configuration: SC)(implicit sourceFactory: DataSourceFactory): DataSource[SC] =
      sourceFactory(configuration)
  }
}
