package com.example.myplayground.domain

import org.junit.Assert
import org.junit.Test

class CalculateExpressionKtTest {

  @Test
  fun testPlus() {
    val expression = "2+2"
    val result = "4"
    Assert.assertEquals(result, calculateExpression(expression, null))
  }

  @Test
  fun testSubtraction() {
    val expression = "5-3"
    val result = "2"
    Assert.assertEquals(result, calculateExpression(expression, null))
  }

  @Test
  fun test() {
    val expression = "999999990/10"
    val result = "99999999"
    Assert.assertEquals(result, calculateExpression(expression, null))
  }

  @Test
  fun testInput() {
    testCalculator("", "")
    testCalculator("2", "2")
    testCalculator("2+", "2")
    testCalculator("2+2", "4")
  }

  private fun testCalculator(
    expression: String,
    result: String
  ) {
    Assert.assertEquals(result, calculateExpression(expression, null))
  }
}