package toulousejug.exo

import org.specs2.mutable._

object FunParserTest extends Specification {

  "Fun expressions parser" should {

    // Tests for integers

    "recognize an integer" in {
      val parsed = FunParser.parseAll(FunParser.number, """123""")
      parsed.successful mustEqual true
    }

    "recognize a negative integer" in {
      val parsed = FunParser.parseAll(FunParser.number, """-123""")
      parsed.successful mustEqual true
    }

    "recognize a positive integer" in {
      val parsed = FunParser.parseAll(FunParser.number, """+123""")
      parsed.successful mustEqual true
    }

    "recognize a positive integer as an expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """+123""")
      parsed.successful mustEqual true
    }

    // To be continued

  }
}