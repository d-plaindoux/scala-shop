package toulousejug.parsers.moustache.syntax

import scala.util.parsing.combinator.JavaTokenParsers

/**
 * Moustache parser using Scala parsers combinator
 */
object MoustacheParser extends JavaTokenParsers {

  // 6 - Internal separator class
  case class Separators(start: String, end: String)

  // 6 - Default separators injected if necessary
  implicit val defaultSeparator = Separators("{{", "}}")

  def startEndLit = """[.->$%&#@!()\[\]]+""".r

  def spaces = """\s+""".r

  // templates
  def template(implicit sep: Separators): Parser[Any] =
    (value(sep) | thenBloc(sep) | textBloc(sep)).*

  // 1 - {{IDENT}}
  // 6 - Add separators
  def value(implicit sep: Separators): Parser[Any] =
    sep.start ~ ident ~ sep.end

  // 2 - {{{IDENT}}} -- escaped
  // 6 - Add separators
  def escaped(implicit sep: Separators): Parser[Any] =
    sep.start ~ "{" ~ ident ~ "}" ~ sep.end

  // 4 - {{#name}} ... {{/name}}
  // 6 - Add separators
  def thenBloc(implicit sep: Separators): Parser[Any] =
    (sep.start ~> "#" ~> ident <~ sep.end) ~ template(sep) >> {
      case name ~ t => sep.start ~> "/" ~> name <~ sep.end
    }

  // 5 - {{START END}} ...
  // 6 - Add separators
  def separators(implicit sep: Separators): Parser[Any] =
    (sep.start ~> startEndLit <~ spaces) ~ (startEndLit <~ sep.end) >> {
      case s ~ e => template(Separators(s, e))
    }

  // 7 - Free text except start separator
  def textBloc(implicit sep: Separators): Parser[Any] =
    sep.start.charAt(0).? ~ s"""[^${sep.start.charAt(0)}]+""".r
}
