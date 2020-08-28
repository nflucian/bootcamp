package ro.neghina.bootcamp.daatloader

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.calcite.avatica.ColumnMetaData.StructType
import org.scalatest.FlatSpec
import ro.neghina.spark.implicits._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DataTypes, StructField}
import ro.neghina.bootcamp.contexts.EntityContext

class AuditHelperSpec extends FlatSpec with DataFrameSuiteBase {
  import spark.implicits._

  it should "rename columns" in {
    val context = EntityContext("test", Map("name" -> "fullName"))
    val persons = Seq((1, "lucian", 40))
    val df = persons.toDF("id", "name", "age")
    val expected = persons.toDF("id", "fullName", "age")

    val response = df.rename(context)

    assertDataFrameEquals(expected, response)
  }

  it should "add audit columns" in {
    val context = EntityContext("test", Map("name" -> "fullName"))
    val persons = Seq((1, "lucian", 40))
    val df = persons.toDF("id", "name", "age")
    val response = df.audit(context)

    val schema = df.schema.add(StructField(context.entity, DataTypes.TimestampType, false))

    assert(schema === response.schema)
  }

}
