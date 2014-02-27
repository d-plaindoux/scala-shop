package toulousejug.parsers.core

import scala.util.parsing.combinator.RegexParsers

trait CoreTokenParsers extends RegexParsers {

  // No space defined
  override protected val whiteSpace = """""".r

  def wrongLit: Parser[String] =
    ???

  def numberLit: Parser[String] =
    """[+-]?\d+""".r

  def stringLit: Parser[String] =
    """('(\\'|[^'])*')|("(\\"|[^"])*")""".r

  // Identifier using Alpha numerical values / Not unicode based
  def identifierLit: Parser[String] =
    """[a-zA-Z][0-9a-zA-Z_$]*""".r

  def operatorLit: Parser[String] =
    """[+/<>*-]+""".r
}