package ro.neghina.spark

import java.net.URL

case class AppArgs(appName: String = "",
                   namespace: Option[String] = None,
                   url: Option[URL] = None,
                   literalConf: Option[String] = None)
