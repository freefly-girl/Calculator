package com.example.myplayground.data

import com.example.myplayground.domain.entity.FormatResultTypeEnum
import com.fathzer.soft.javaluator.DoubleEvaluator
import kotlin.math.floor

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

    val solution = DoubleEvaluator().evaluate(formattedExpression)

    // TODO: 05.11.2021 DigitsAfterPoint
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