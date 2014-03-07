package toulousejug.exo

import toulousejug.parsers.core.CoreTokenParsers
import scala.util.parsing.combinator.{JavaTokenParsers, PackratParsers}

/**
 * Moustache parser using Scala parsers combinator
 */
object MustacheParser extends CoreTokenParsers {

  // templates main parser
  def template: Parser[Any] =
    ???

  // step 1 - {{IDENT}}
  def value =
    "{{" ~ identifierLit ~ "}}"

  // step 2 - {{{IDENT}}} -- escaped
  def escaped =
    ???

  // step 4 - {{#name}} ... {{/name}}
  def thenBloc =
    ???

  // step 5 - Free text except start separator
  def textBloc =
    ???

  // step 6 - {{START END}} defining start/end literal
  def startEndLit =
    ???

  // step 7 - Default separators

  // step 8 - Implicit default separators

  // step 9 - Add separators ..;
  def separators =
    ???

}
