import com.typesafe.config.{Config, ConfigFactory}
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

import scala.util.matching.Regex

package object params {
  val applicationConf: Config = ConfigFactory.load()
  lazy implicit val config: ApiParams = applicationConf.as[ApiParams]("app")

  case class ApiParams(userName: String,
                       token: String,
                       psqlName: String
                      )
}

package object regex {
  val addSubscribeCommand: Regex = "/add\\shttps://t.me/([a-zA-Z0-9-_]+)".r
  val deleteChannelCommand: Regex = "/delete\\shttps://t.me/([a-zA-Z0-9-_]+)".r
  val deleteAllSubscribesCommand: Regex = "/deleteall".r
  val showSubscribesCommand: Regex = "/show".r
  val infoCommand: Regex = "info".r
}