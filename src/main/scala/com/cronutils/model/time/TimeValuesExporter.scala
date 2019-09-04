package com.cronutils.model.time

import scala.jdk.CollectionConverters._

import com.cronutils.model.field.CronField
import com.cronutils.model.time.generator.FieldValueGeneratorFactory

// TimeValueExporter meeds to be in this package since TimeNode is declared as package private
object TimeValuesExporter {

  /**
  *  Exports time value from a `CronField`.
   *
   * @param cronField the cron field of the time unit to export (e.g. minutes, hours, day of month, etc)
   *
   * @return a list of integers representing execution times for the time unit
   */
  def exportValues(cronField: CronField): List[Int] = {
    val startRange = cronField.getConstraints.getStartRange
    val endRange = cronField.getConstraints.getEndRange
    new TimeNode(FieldValueGeneratorFactory
      .forCronField(cronField)
      .generateCandidates(startRange, endRange))
      .values
      .asScala
      .map(_.intValue())
      .toList
  }
}
