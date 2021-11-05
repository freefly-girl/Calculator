package com.example.myplayground.data

import com.fathzer.soft.javaluator.DoubleEvaluator
import kotlin.math.floor

/**
 * Рассчитывает значение выражения [expression]
 */

fun calculateExpression(expression: String): String {

  if (expression.isBlank()) return ""

  // пока последний символ не число, убираем последний элемент строки
  var formattedExpression = expression
  while (!formattedExpression.last().isDigit()) {
    formattedExpression = formattedExpression.dropLast(1)
  }

//  if (!formattedExpression.isEmpty()) {
//    formattedExpression = formattedExpression
//  }

  val result = DoubleEvaluator().evaluate(formattedExpression)

  return if (floor(result) == result) {
    result.toInt().toString()
  } else {
    result.toString()
  }
}