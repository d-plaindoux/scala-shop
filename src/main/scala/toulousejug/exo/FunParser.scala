package toulousejug.exo

import toulousejug.parsers.core.CoreTokenParsers
import scala.util.parsing.combinator.Parsers

object FunParser extends CoreTokenParsers with Parsers {

  // -------------------------------------------------------------------------------------------------------------------
  // Expressions
  // -------------------------------------------------------------------------------------------------------------------

  def expression: Parser[Any] =
    numberLit

  // Step 1 - Parse integers
  def number =
    numberLit

  // Step 2 - Parse strings (cf. CoreTokenParsers implicits)
  def string =
    ???

  // Step 3 - Parse identifiers (cf. CoreTokenParsers implicits)
  def identifier =
    ???

  // Step 5 - Parenthesis expression as expression
  def parenthesis =
    ???

  // Step 6 - spaces - overrides existing spaces

  // Step 7 - Array expression
  def array =
    ???

  // Step 8 - Record expression
  def record =
    ???

  // Step 9 - Function call expression | Non deterministic case wen dealing with identifier
  def funcall =
    ???

  // Step 10 - Infix expressions
  def infix =
    ???

  // -------------------------------------------------------------------------------------------------------------------
  // Statements
  // -------------------------------------------------------------------------------------------------------------------

  // Step 11 - Statement
  def statement: Parser[Any] =
    ???

  // Step 12 - Return statement
  def aReturn =
    ???

  // Step 13 - If Then Else
  def ifThenElse =
    ???

  // Step 14 - Variable specification
  def variable =
    ???

  // Step 15 - Statement bloc
  def statements =
    ???

  // Step 16 - Statement bloc
  def function =
    ???

  // Step 17 - Expression as a statement
  def statementExpression =
    ???

}
