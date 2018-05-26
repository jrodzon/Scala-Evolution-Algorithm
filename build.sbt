name := "scage-test"

version := "0.1"

scalaVersion := "2.11.0"

// https://mvnrepository.com/artifact/org.scala-lang/scala-swing
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"

lazy val akkaVersion = "2.5.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)