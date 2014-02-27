package toulousejug.exo

import org.specs2.mutable._
import scala.util.parsing.combinator.lexical.{Lexical, Scanners}

object MustacheParserTest extends Specification {

  "Mustache expressions parser" should {

    "recognize a simple identifier" in {
      val parsed = MustacheParser.parseAll(MustacheParser.value, """{{ident}}""")
      parsed.successful mustEqual true
    }

    // To be continued
  }

}