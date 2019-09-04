package com.deliveroo.cronexpressionparser

import com.deliveroo.cronexpressionparser.ParserError.{InvalidNumberOfArguments, InvalidTimeExpression}
import org.scalatest.{EitherValues, FlatSpec, Matchers}

class ParserSpec extends FlatSpec with Matchers with EitherValues {

  "A CronParser" should "correctly parse the expression \"*/15 0 1,15 * 1-5 /usr/bin/find\"" in {
    val expression = "*/15 0 1,15 * 1-5 /usr/bin/find".split(" ")
    val result = CronParser.parse(expression)

    val expectedResult = """minute         0 15 30 45
                           |hour           0
                           |day of month   1 15
                           |month          1 2 3 4 5 6 7 8 9 10 11 12
                           |day of week    1 2 3 4 5
                           |command       /usr/bin/find""".stripMargin

    result.getOrElse() shouldBe expectedResult
  }

  it should "not parse expressions without at least 6 arguments" in {
    val expression = "0 0 0 0 /usr/bin/find".split(" ")
    val result = CronParser.parse(expression)

    val expectedError = InvalidNumberOfArguments(5, 6)

    result.left.value shouldBe expectedError
  }

  it should "not parse invalid expressions" in {
    val expression = "*/61 0 1,15 * 1-5 /usr/bin/find".split(" ")
    val result = CronParser.parse(expression)

    val expectedMessage = """Can't parse expression "*/61 0 1,15 * 1-5"
                            |
                            |Underlying exception: Failed to parse '*/61 0 1,15 * 1-5'. Period 61 not in range (0, 59]""".stripMargin

    result.left.value shouldBe a [InvalidTimeExpression]
    result.left.value.code shouldBe 200
    result.left.value.asString shouldBe expectedMessage
  }


}
