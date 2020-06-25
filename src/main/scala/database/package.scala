package database

import cats.effect._
import doobie._
import doobie.util.ExecutionContexts
import params.config

object DatabaseConnection {

  implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",               // driver classname
    s"jdbc:postgresql:${config.psqlName}", // connect URL (driver-specific)
    "kudryashovaia",                       // user
    "Meg4Deve10per^^",                     // password
    Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
  )

}
