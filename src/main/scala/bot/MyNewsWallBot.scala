package bot

import cats.instances.future._
import cats.syntax.functor._
import com.bot4s.telegram.api.RequestHandler
import com.bot4s.telegram.api.declarative.{Commands, RegexCommands}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.future.{Polling, TelegramBot}
import com.bot4s.telegram.methods.SendMessage
import com.bot4s.telegram.models.{Update, User}
import com.bot4s.telegram.models.InlineKeyboardMarkup

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Try
import regex._
import tdapi.TgApi

class MyNewsWallBot(val token: String) extends TelegramBot
  with Polling
  with Commands[Future]
  with RegexCommands[Future] {


//  object Int {
//    def unapply(s: String): Option[Int] = Try(s.toInt).toOption
//  }
//
//  object String {
//    def unapply(s: String): Option[String] = Try(s).toOption
//  }
//
//  onCommand("/hello") { implicit msg =>
//    reply("Hello America!").void
//  }
//
//  onCommand("/inc") { implicit msg =>
//    withArgs {
//      case Seq(Int(i)) =>
//        reply("" + (i + 1)).void
//
//      // Conveniently avoid MatchError, providing hints on usage.
//      case _ =>
//        reply("Invalid argument. Usage: /inc 123").void
//    }
//  }
//
//  onRegex(regex.addSubscribeCommand) { implicit msg => {
//    case Seq(String(mm)) =>
//      reply(s"here: $mm").void
//
//  }
//  }

  val myId: Long = 482946391L

  override def receiveUpdate(u: Update, botUser: Option[User]): Future[Unit] = {
    if (u.message.isDefined) {
      //request(SendMessage(u.message.get.source,  s"${u.message.getOrElse("")}")).void

      u.message.map(_.text.getOrElse("")).getOrElse("") match {
        case addSubscribeCommand(s) => {
          val channel = TgApi.findChannelByName(s)
          request(SendMessage(u.message.get.source,  channel.toString)).void
        }


      }

//      u.message.get.text.fold(Future.successful(())) { text =>
//        request(SendMessage(u.message.get.source, text.reverse + s"${u.message.get.source}")).void
//      }
      //request(sendInlineKeyBoardMessage(u.message.get.chat.id)).void
    }
      if (u.callbackQuery.isDefined) {
        request(SendMessage(u.callbackQuery.get.from.id.toLong, u.callbackQuery.get.message.get.text.get)).void
      }
    else Future()
  }

  override val client: RequestHandler[Future] = new ScalajHttpClient(token)

  import com.bot4s.telegram.methods.SendMessage
  import com.bot4s.telegram.models.InlineKeyboardButton
  import com.bot4s.telegram.models.InlineKeyboardMarkup

  def sendInlineKeyBoardMessage(chatId: Long): SendMessage = {
    val inlineKeyboardMarkup = InlineKeyboardMarkup.singleButton(InlineKeyboardButton("button", callbackData = Some("button1 has been pressed")))
    SendMessage(chatId, "Пример", replyMarkup = Some(inlineKeyboardMarkup))
  }
}
