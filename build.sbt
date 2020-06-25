name := "MyWall"

version := "0.1"

scalaVersion := "2.12.7"

scalacOptions += "-Ypartial-unification"

lazy val doobieVersion = "0.8.8"

libraryDependencies ++= Seq(
  "com.bot4s" %% "telegram-core" % "4.4.0-RC2",
  "com.bot4s" %% "telegram-akka" % "4.4.0-RC2",
  "com.iheart" %% "ficus" % "1.4.0",
  "org.tpolecat" %% "doobie-core"     % doobieVersion,
  "org.tpolecat" %% "doobie-postgres" % doobieVersion,
  "org.tpolecat" %% "doobie-specs2"   % doobieVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)