package com.example.myplayground.domain

import com.example.myplayground.domain.entity.FormatResultTypeEnum
import com.fathzer.soft.javaluator.DoubleEvaluator
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.floor

val mc = MathContext(5)
val evaluator = DoubleEvaluator(DoubleEvaluator.getDefaultParameters(), true)

/**
 * Рассчитывает значение выражения [expression]
 */

fun calculateExpression(expression: String, formatResultType: FormatResultTypeEnum?): String {

  try {
    if (expression.isBlank()) return ""

    // пока последний символ не число, убираем последний элемент строки
    var formattedExpression = expression
    while (!formattedExpression.last().isDigit()) {
      formattedExpression = formattedExpression.dropLast(1)
    }

//    val solution = DoubleEvaluator().evaluate(formattedExpression)

    val solution = BigDecimal(evaluator.evaluate(formattedExpression), mc).toDouble()

    val result = when (formatResultType) {
      FormatResultTypeEnum.ONE -> String.format("%.1f", solution).toDouble()
      FormatResultTypeEnum.TWO -> String.format("%.2f", solution).toDouble()
      FormatResultTypeEnum.TREE -> String.format("%.3f", solution).toDouble()
      FormatResultTypeEnum.MANY -> solution
      null -> solution
    }

    return if (floor(result) == result) {
      result.toInt().toString()
    } else {
      result.toString()
    }
  } catch (exc: NumberFormatException) {
    return "Ошибка"
  }
}
