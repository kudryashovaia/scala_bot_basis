package main

import bot.MyNewsWallBot
import database.DatabaseConnection
import doobie.implicits._
import params.config


object Main extends App {

  val databaseConnection = DatabaseConnection
  import databaseConnection._

  val bot = new MyNewsWallBot(config.token)
  val eol = bot.run()
  println("Press [ENTER] to shutdown the bot, it may take a few seconds...")
  scala.io.StdIn.readLine()
  bot.shutdown()
//   Wait for the bot end-of-life
//  Await.result(eol, Duration.Inf)
// psql usage
//  val program1 =  sql"select channel_id from channel_user".query[Int].unique
//
//  val io = program1.transact(xa)
//  // io: IO[Int] = Async(
//  //   cats.effect.internals.IOBracket$$$Lambda$8180/248688506@6db044f4,
//  //   false
//  // )
//  println(io.unsafeRunSync)


}
