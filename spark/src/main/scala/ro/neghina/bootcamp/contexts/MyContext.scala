package ro.neghina.bootcamp.contexts

import ro.esolutions.spark.io.sinks.SinkConfiguration
import ro.esolutions.spark.io.sources.SourceConfiguration

case class MyContext(input: SourceConfiguration, output: SinkConfiguration)