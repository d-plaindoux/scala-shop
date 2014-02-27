package toulousejug.parsers.mustache.syntax

import org.specs2.mutable._

object MustacheParserTest extends Specification {

  "Moustache expressions parser" should {

    "recognize a simple identifier" in {
      val parsed = MustacheParser.parseAll(MustacheParser.value, """{{ident}}""")
      parsed.successful mustEqual true
    }

    "recognize a bloc" in {
      val parsed = MustacheParser.parseAll(MustacheParser.thenBloc, """{{#ident}}{{/ident}}""")
      parsed.successful mustEqual true
    }

    "reject a bloc" in {
      val parsed = MustacheParser.parseAll(MustacheParser.thenBloc, """{{#ident1}}{{/ident2}}""")
      parsed.successful mustEqual false
    }

    "recognize a text" in {
      val parsed = MustacheParser.parseAll(MustacheParser.textBloc, """Hello, World""")
      parsed.successful mustEqual true
    }

    "reject a text containing a separator" in {
      val parsed = MustacheParser.parseAll(MustacheParser.textBloc, """Hello, World{{""")
      parsed.successful mustEqual false
    }

    "accept separator redefinition" in {
      val parsed = MustacheParser.parseAll(MustacheParser.separators, """{{<< >>}}<<toto>>{{titi""")
      parsed.successful mustEqual true
    }
  }
}