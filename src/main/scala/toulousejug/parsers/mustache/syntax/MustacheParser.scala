package toulousejug.parsers.mustache.syntax

import scala.util.parsing.combinator.JavaTokenParsers
import toulousejug.parsers.core.CoreTokenParsers

/**
 * Moustache parser using Scala parsers combinator
 */
object MustacheParser extends CoreTokenParsers {

  // 6 - Internal separator class
  case class Separators(start: String, end: String)

  // 6 - Default separators injected if necessary
  implicit val defaultSeparator = Separators("{{", "}}")

  def startEndLit = """[.->$%&#@!()\[\]]+""".r

  def spacesLit = """\s+""".r

  // templates
  def template(implicit sep: Separators): Parser[Any] =
    (value(sep) | thenBloc(sep) | textBloc(sep) | separators(sep)).*

  // 1 - {{IDENT}}
  // 6 - Add separators
  def value(implicit sep: Separators): Parser[Any] =
    sep.start ~ identifierLit ~ sep.end

  // 2 - {{{IDENT}}} -- escaped
  // 6 - Add separators
  def escaped(implicit sep: Separators): Parser[Any] =
    sep.start ~ "{" ~ identifierLit ~ "}" ~ sep.end

  // 4 - {{#name}} ... {{/name}}
  // 6 - Add separators
  def thenBloc(implicit sep: Separators): Parser[Any] =
    (sep.start ~> "#" ~> identifierLit <~ sep.end) ~ template(sep) >> {
      case name ~ t => sep.start ~> "/" ~> name <~ sep.end
    }

  // 5 - {{START END}} ...
  // 6 - Add separators
  def separators(implicit sep: Separators): Parser[Any] =
    (sep.start ~> startEndLit <~ spacesLit) ~ (startEndLit <~ sep.end) >> {
      case s ~ e => template(Separators(s, e))
    }

  // 7 - Free text except start separator
  def textBloc(implicit sep: Separators): Parser[Any] =
    sep.start.charAt(0).? ~ s"""[^${sep.start.charAt(0)}]+""".r
}
