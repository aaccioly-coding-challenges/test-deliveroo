package com.deliveroo.cronexpressionparser

import com.cronutils.model.definition.CronDefinitionBuilder
import com.cronutils.model.{Cron, CronType}
import com.cronutils.parser.CronParser
import com.deliveroo.cronexpressionparser.ParserError.{InvalidNumberOfArguments, InvalidTimeExpression, ParserError, UnknownError}

object CronParser {

  private val MinimumNumberOfArguments = 6

  def parse(args: Array[String]): Either[ParserError, String] = {
    inputArguments(args)
      .flatMap {
        case (timeExpression, command) => {cronTabExpression(timeExpression).map(Right(_, command))
      }.flatMap {
        case Right((cronExpression,command)) => formattedTable(cronExpression, command)
      }
    }
  }

  /**
  *  Split command line arguments into a Tuple where the first element is the time expression and the second is the
   * command to run.
   *
   * @param args command line arguments
   * @return either an error or the parsed time expression and command to execute
   */
  def inputArguments(args: Array[String]): Either[ParserError, (String, String)] = {
    if (args.length < MinimumNumberOfArguments) {
      Left(InvalidNumberOfArguments(args.length, MinimumNumberOfArguments))
    }
    else {
      val (expression, command) = args.splitAt(5)
      Right(expression.mkString(" "), command.mkString(" "))
    }
  }

  def cronTabExpression(timeExpression: String): Either[ParserError, Cron] = {
    val cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX)
    val parser = new CronParser(cronDefinition)
    try {
      Right(parser.parse(timeExpression))
    } catch {
      case e: Throwable => Left(InvalidTimeExpression(timeExpression, e))
    }
  }

  def formattedTable(cronExpression: Cron, command: String): Either[ParserError, String] = {
    try {
      Right(ExpressionFormatter.formatExpressionTable(cronExpression, command))
    } catch {
      case e: Throwable => Left(UnknownError(s"Couldn't format ${cronExpression.asString}", Some(e)))
    }
  }

}
