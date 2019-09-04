import sbt._

object Dependencies {
  lazy val cronUtils = "com.cronutils" % "cron-utils" % "9.0.1"
  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
}
