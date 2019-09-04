package com.deliveroo.cronexpressionparser

import com.cronutils.model.Cron
import com.cronutils.model.field.{CronField, CronFieldName}
import com.cronutils.model.time.TimeValuesExporter

object ExpressionFormatter {

  def cronFieldNameToCaption(cronFieldName: CronFieldName): String = {
    cronFieldName.toString.toLowerCase().replace('_', ' ')
  }

  def formatField(cronField: CronField): String = {
   val timeUnitCaption = f"${cronFieldNameToCaption(cronField.getField)}%-14s"
   val timeValues = TimeValuesExporter.exportValues(cronField).mkString(" ")

    s"$timeUnitCaption $timeValues"
  }

  def formatCommand(command: String): String = {
    s"command       $command"
  }

  def formatExpressionTable(cronExpression: Cron, command: String): String = {
    val expressionString = new StringBuilder()

    cronExpression.retrieveFieldsAsMap().values().forEach{ cronField =>
      expressionString ++= formatField(cronField)
      expressionString += '\n'
    }

    expressionString ++= formatCommand(command)

    expressionString.toString
  }

}
