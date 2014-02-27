package toulousejug.parsers.moustache.syntax

import org.specs2.mutable._

object MoustacheParserTest extends Specification {

  "Moustache expressions parser" should {

    "recognize a simple identifier" in {
      val parsed = MoustacheParser.parseAll(MoustacheParser.value, """{{ident}}""")
      parsed.successful mustEqual true
    }

    "recognize a bloc" in {
      val parsed = MoustacheParser.parseAll(MoustacheParser.thenBloc, """{{#ident}}{{/ident}}""")
      parsed.successful mustEqual true
    }

    "reject a bloc" in {
      val parsed = MoustacheParser.parseAll(MoustacheParser.thenBloc, """{{#ident1}}{{/ident2}}""")
      parsed.successful mustEqual false
    }

    "recognize a text" in {
      val parsed = MoustacheParser.parseAll(MoustacheParser.textBloc, """Hello, World""")
      parsed.successful mustEqual true
    }

    "reject a text containing a separator" in {
      val parsed = MoustacheParser.parseAll(MoustacheParser.textBloc, """Hello, World{{""")
      parsed.successful mustEqual false
    }

    "accept separator redefinition" in {
      val parsed = MoustacheParser.parseAll(MoustacheParser.separators, """{{<< >>}}<<toto>>{{titi""")
      parsed.successful mustEqual true
    }
  }
}