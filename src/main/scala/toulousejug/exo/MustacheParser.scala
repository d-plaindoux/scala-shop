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

  // 1 - {{IDENT}}
  def value =
    "{{" ~ identifierLit ~ "}}"

  // 2 - {{{IDENT}}} -- escaped
  def escaped =
    ???

  // 4 - {{#name}} ... {{/name}}
  def thenBloc =
    ???

  // 8 - Free text except start separator
  def textBloc =
    ???

  // 5 - {{START END}} defining start/end literal
  def startEndLit =
    ???

  // 6 - Default separators injected if necessary

  // 7 - Add separators
  def separators =
    ???

}
