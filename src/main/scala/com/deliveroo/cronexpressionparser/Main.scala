package com.deliveroo.cronexpressionparser

object Main extends App {

  CronParser.parse(args) match {
    case Right(output) =>
      println(output)
    case Left(error) =>
      System.err.println(error.asString())
      sys.exit(error.code())
  }

}
