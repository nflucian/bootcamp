package ro.neghina.spark

import org.apache.spark.internal.Logging
import org.apache.spark.sql.SparkSession
import pureconfig.{ConfigObjectSource, ConfigSource}

trait ConfigBuilder extends Logging {

  private[spark] def appConfiguration(conf: AppArgs)(implicit spark: SparkSession): ConfigSource = {

    log.info(s"${conf.appName} build config:\n$conf")

    val literalConfiguration: Option[ConfigObjectSource] = conf.literalConf.map(l => ConfigSource.string(l))
    val urlConfiguration: Option[ConfigObjectSource] = conf.url.map(u => ConfigSource.url(u))

    val configurationSources: Seq[Option[ConfigObjectSource]] = Seq(literalConfiguration, urlConfiguration)

    val confObj = configurationSources.collect{ case Some(config) => config }
      .fold(ConfigSource.empty)((x, y) => x.withFallback(y))
      .withFallback(ConfigSource.default)

    conf.namespace
      .map(n => confObj.at(n))
      .getOrElse(confObj)
  }

}