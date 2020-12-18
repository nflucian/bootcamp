package ro.neghina.bootcamp.contexts

import ro.esolutions.spark.io.sinks.SinkConfiguration
import ro.esolutions.spark.io.sources.SourceConfiguration

case class DataSource(name: String, input: SourceConfiguration)

case class SqlContext(source: List[DataSource], output: SinkConfiguration, query: String)