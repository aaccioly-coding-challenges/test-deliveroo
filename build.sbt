import Dependencies._

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.deliveroo"
ThisBuild / organizationName := "Deliveroo"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    maintainer := "a.accioly@7rtc.com",
    name := "Cron Expression Parser",
    description := "Technical Task - Cron Expression Parser",
    libraryDependencies ++= Seq(
      cronUtils,
      logback,
      scalaTest % Test
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
