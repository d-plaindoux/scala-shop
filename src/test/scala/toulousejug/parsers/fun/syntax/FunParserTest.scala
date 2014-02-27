package toulousejug.parsers.fun.syntax

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

    // Tests for strings

    "recognize a quoted string" in {
      val parsed = FunParser.parseAll(FunParser.string, """'toto'""")
      parsed.successful mustEqual true
    }

    "recognize a quoted string with a quote" in {
      val parsed = FunParser.parseAll(FunParser.string, """'to\'to'""")
      parsed.successful mustEqual true
    }

    // Tests for identifier

    "recognize an identifier" in {
      val parsed = FunParser.parseAll(FunParser.identifier, """This_Is_A_simple_Identifier""")
      parsed.successful mustEqual true
    }

    "do not recognize a not well formed identifier" in {
      val parsed = FunParser.parseAll(FunParser.identifier, """This_Is_A_simple-Identifier""")
      parsed.successful mustEqual false
    }

    // Test for expression

    "recognize an integer expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """123""")
      parsed.successful mustEqual true
    }

    "recognize a string expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """"toto"""")
      parsed.successful mustEqual true
    }

    "recognize an identifier expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """This_Is_A_simple_Identifier""")
      parsed.successful mustEqual true
    }

    // Test for expression in parenthesis

    "recognize an expression in parenthesis" in {
      val parsed = FunParser.parseAll(FunParser.expression, """(123)""")
      parsed.successful mustEqual true
    }

    "recognize an expression in parenthesis with spaces" in {
      val parsed = FunParser.parseAll(FunParser.parenthesis, """( 123 )""")
      parsed.successful mustEqual true
    }

    "recognize an expression in parenthesis as an expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """( 123 )""")
      parsed.successful mustEqual true
    }

    // Test for array expression

    "recognize an empty array" in {
      val parsed = FunParser.parseAll(FunParser.array, """[]""")
      parsed.successful mustEqual true
    }

    "recognize an empty array expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """[]""")
      parsed.successful mustEqual true
    }

    "recognize a non empty array" in {
      val parsed = FunParser.parseAll(FunParser.array, """[1, "hello"]""")
      parsed.successful mustEqual true
    }

    "recognize a non empty array expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """[ 1, "hello" ]""")
      parsed.successful mustEqual true
    }

    // Test for record expression

    "recognize an empty record" in {
      val parsed = FunParser.parseAll(FunParser.record, """{}""")
      parsed.successful mustEqual true
    }

    "recognize an empty record expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """{}""")
      parsed.successful mustEqual true
    }

    "recognize a non empty record" in {
      val parsed = FunParser.parseAll(FunParser.record, """{a: 123, "X-Content":json}""")
      parsed.successful mustEqual true
    }

    "recognize a non empty record expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """{a: 123, "X-Content":json}""")
      parsed.successful mustEqual true
    }

    // Test for function call

    "recognize a function call" in {
      val parsed = FunParser.parseAll(FunParser.funcall, """reduce(1,"toto")""")
      parsed.successful mustEqual true
    }

    "recognize a function call expression" in {
      val parsed = FunParser.parseAll(FunParser.expression, """reduce(1,"toto")""")
      parsed.successful mustEqual true
    }

    "recognize an infix" in {
      val parsed = FunParser.parseAll(FunParser.infix, """1+2""")
      parsed.successful mustEqual true
    }
  }

  "Fun statement parser" should {
    "recognize a return " in {
      val parsed = FunParser.parseAll(FunParser.aReturn, """return 1;""")
      parsed.successful mustEqual true
    }

    "recognize a return statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """return reduce(1,"toto");""")
      parsed.successful mustEqual true
    }

    "recognize a return " in {
      val parsed = FunParser.parseAll(FunParser.aReturn, """return 1;""")
      parsed.successful mustEqual true
    }

    "recognize a return statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """return reduce(1,"toto");""")
      parsed.successful mustEqual true
    }

    "recognize an if/then/else" in {
      val parsed = FunParser.parseAll(FunParser.ifThenElse, """if true then 1; else return "a";""")
      parsed.successful mustEqual true
    }

    "recognize an if/then/else statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """if true then 1; else return "a";""")
      parsed.successful mustEqual true
    }

    "recognize a variable declaration statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """var a;""")
      parsed.successful mustEqual true
    }

    "recognize a variable statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """var a = 123 + 23;""")
      parsed.successful mustEqual true
    }

    "recognize variables declaration statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """var a, b;""")
      parsed.successful mustEqual true
    }

    "recognize variables statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """var a = 123 + 23, b;""")
      parsed.successful mustEqual true
    }

    "recognize empty bloc statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """{}""")
      parsed.successful mustEqual true
    }

    "recognize bloc statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """{ return 123; }""")
      parsed.successful mustEqual true
    }

    "recognize function statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """function a() { return 123; }""")
      parsed.successful mustEqual true
    }

    "recognize function statement with parameters" in {
      val parsed = FunParser.parseAll(FunParser.statement, """function add(x,y) { return x + y; }""")
      parsed.successful mustEqual true
    }

    "recognize expression statement" in {
      val parsed = FunParser.parseAll(FunParser.statement, """x + y;""")
      parsed.successful mustEqual true
    }
  }
}