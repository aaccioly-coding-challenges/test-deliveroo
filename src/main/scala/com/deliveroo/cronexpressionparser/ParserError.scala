package com.deliveroo.cronexpressionparser

object ParserError {
  sealed trait ParserError {
    def code(): Int
    def message(): String
    def underlyingException(): Option[Throwable] = None

    def asString(): String = {
      val stringBuilder = new StringBuilder(message())
      if (underlyingException().isDefined) {
        stringBuilder ++= s"\n\nUnderlying exception: ${underlyingException().get.getMessage}"
      }

      stringBuilder.toString()
    }
  }

  final case class InvalidNumberOfArguments(actualLength: Int, expectedLength: Int) extends ParserError {
    val code: Int = 100
    val message: String = s"Invalid input, expecting at least $expectedLength arguments. Received $actualLength"
  }

  final case class InvalidTimeExpression(timeExpression: String, exception: Throwable) extends ParserError {
    val code: Int = 200
    val message: String = s"""Can't parse expression "$timeExpression""""
    override val underlyingException = Some(exception)
  }

  final case class UnknownError(message: String,
                                override val underlyingException: Option[Throwable] = None) extends  ParserError {
    override def code(): Int = 300
  }
}
