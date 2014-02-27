package toulousejug.exo

import org.specs2.mutable._

object MoustacheParserTest extends Specification {

  "Moustache expressions parser" should {

    "recognize a simple identifier" in {
      val parsed = MoustacheParser.parseAll(MoustacheParser.value, """{{ident}}""")
      parsed.successful mustEqual true
    }

    // To be continued
  }
}