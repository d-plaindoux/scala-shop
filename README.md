scala-shop
==========

"Scala Parsers Combinators" decicated coding dojo.


Introduction
------------

This coding dojo introduces Scala parsers combinators standard library. 
This is done performing parsers for two different mini-languages. 

The first one is inspired by Javacscript expressions and is by definition a regular 
language. The main difficulty can be the left recursion which can be managed
in different ways.

The second language is inspired by Mustach. This case is a little more complex
for two different reasons. First it's not a regular language and second its
syntax can change during the analysis. For those two reasons this last case study
shows the possibilities offered by such a parser library and related concept
like combinators ... and monads of course.

How-to
------

Exercises are in the directory named [exos](https://github.com/d-plaindoux/scala-shop/tree/master/src/main/scala/toulousejug/exo) 
and directives are given by comments in the source code. In addition a complete solution for both mini-language is also given in the directory [parsers](https://github.com/d-plaindoux/scala-shop/tree/master/src/main/scala/toulousejug/parsers).

In parallel a set of slides in french (using Reveal.js) are provided.

