package toulousejug.parsers.fun.syntax

import toulousejug.parsers.core.CoreTokenParsers
import scala.util.parsing.combinator.Parsers
import toulousejug.parsers.fun.ast._
import toulousejug.parsers.fun.ast.IdentExpression
import toulousejug.parsers.fun.ast.IntegerExpression
import toulousejug.parsers.fun.ast.StringExpression

object FunParser extends CoreTokenParsers with Parsers {

  // -------------------------------------------------------------------------------------------------------------------
  // Expressions
  // -------------------------------------------------------------------------------------------------------------------

  def expression: Parser[Expression] =
    infix | simpleExpression

  def simpleExpression: Parser[Expression] =
    number | string /* | identifier */ | parenthesis | array | record | funcall

  // Step 1 - Parse integers
  def number: Parser[IntegerExpression] =
    numberLit ^^ (v => IntegerExpression(v.toInt))

  // Step 2 - Parse strings
  def string: Parser[StringExpression] =
    stringLit ^^ StringExpression

  // Step 3 - Parse identifiers
  def identifier: Parser[IdentExpression] =
    identifierLit ^^ IdentExpression

  // Step 5 - Parenthesis expression as expression
  def parenthesis: Parser[Expression] =
    "(" ~> expression <~ ")"

  // Step 6 - spaces - overrides existing spaces
  override protected val whiteSpace = """\s+""".r

  // Step 7 - Array expression
  def array: Parser[ArrayExpression] =
    "[" ~> repsep(expression, ",") <~ "]" ^^ ArrayExpression

  // Step 8 - Record expression
  def record: Parser[RecordExpression] =
    "{" ~> repsep((identifier | string) ~ (":" ~> expression), ",") <~ "}" ^^ {
      l => RecordExpression(for ((k ~ v) <- l) yield (k.toString, v))
    }

  // Step 9 - Function call expression | Non deterministic case wen dealing with identifier
  def funcall: Parser[Expression] =
    identifier ~ ("(" ~> repsep(expression, ",") <~ ")").? ^^ {
      case e ~ l => FunCallExpression(e.toString, l.getOrElse(Nil))
    }

  // Step 10 - Infix expressions
  def infix: Parser[InfixExpression] =
    simpleExpression ~ operatorLit ~ expression ^^ {
      case l ~ op ~ r => InfixExpression(l, op, r)
    }

  // -------------------------------------------------------------------------------------------------------------------
  // Statements
  // -------------------------------------------------------------------------------------------------------------------

  // Step 11 - Statement
  def statement: Parser[Statement] =
    aReturn | ifThenElse | variable | statements | function | statementExpression

  // Step 12 - Return statement
  def aReturn: Parser[ReturnStatement] =
    "return" ~> expression <~ ";" ^^ ReturnStatement

  // Step 13 - If Then Else
  def ifThenElse: Parser[IfThenElse] =
    "if" ~> expression ~ ("then" ~> statement) ~ ("else" ~> statement <~ ";".?) ^^ {
      case c ~ t ~ f => IfThenElse(c, null, null)
    }

  // Step 14 - Variable specification
  def variable: Parser[VariablesStatement] =
    "var" ~> repsep(identifier ~ ("=" ~> expression).?, ",") <~ ";" ^^ {
      l => VariablesStatement(for ((n ~ v) <- l) yield (n.toString, v))
    }

  // Step 15 - Statement bloc
  def statements: Parser[BlocStatement] =
    "{" ~> rep(statement) <~ "}" <~ ";".? ^^ BlocStatement

  // Step 16 - Statement bloc
  def function: Parser[FunctionStatement] =
    "function" ~> identifierLit ~ ("(" ~> repsep(expression, ",") <~ ")") ~ statements ^^ {
      case id ~ params ~ bloc => FunctionStatement(id, params, bloc)
    }

  // Step 17 - Expression as a statement
  def statementExpression: Parser[ExpressionStatement] =
    expression <~ ";" ^^ ExpressionStatement
}
