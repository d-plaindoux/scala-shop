package toulousejug.exo

import org.specs2.mutable._

object MustacheParserTest extends Specification {

  "Moustache expressions parser" should {

    "recognize a simple identifier" in {
      val parsed = MustacheParser.parseAll(MustacheParser.value, """{{ident}}""")
      parsed.successful mustEqual true
    }

    // To be continued
  }
}