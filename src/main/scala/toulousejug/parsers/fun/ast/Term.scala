package toulousejug.parsers.fun.ast

import scala.util.parsing.input.Positional

// ---------------------------------------------------------------------------------------------------------------------
// Expressions
// ---------------------------------------------------------------------------------------------------------------------

sealed trait Expression extends Positional

case class IntegerExpression(value: Int) extends Expression

case class StringExpression(value: String) extends Expression

case class IdentExpression(name: String) extends Expression

case class ArrayExpression(value: List[Expression]) extends Expression

case class RecordExpression(value: List[(String, Expression)]) extends Expression

case class FunCallExpression(name: String, params: List[Expression]) extends Expression

case class InfixExpression(left: Expression, operator: String, right: Expression) extends Expression

// ---------------------------------------------------------------------------------------------------------------------
// Statements
// ---------------------------------------------------------------------------------------------------------------------

sealed trait Statement extends Positional

case class ReturnStatement(value: Expression) extends Statement

case class IfThenElse(cond: Expression, whenTrue: Statement, whenFalse: Statement) extends Statement

case class VariablesStatement(vars: List[(String, Option[Expression])]) extends Statement

case class BlocStatement(statements: List[Statement]) extends Statement

case class FunctionStatement(s: String, expressions: List[Expression], statement: BlocStatement) extends Statement

case class ExpressionStatement(expression: Expression) extends Statement

