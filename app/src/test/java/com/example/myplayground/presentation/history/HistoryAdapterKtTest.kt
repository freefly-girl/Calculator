package com.example.myplayground.presentation.history

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class HistoryAdapterKtTest {

  @Test
  fun testFormatter() {
    val date = LocalDateTime.of(2021, 10, 25, 13, 2)
    val result = date.formatForHistory()

    assertEquals("13:02 25 окт. 2021", result)
  }
}