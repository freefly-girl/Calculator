package com.example.myplayground

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun multiplication_isCorrect() {
        assertEquals(4, 2 * 2)
    }

    @Test
    fun decrement_isCorrect() {
        var a = 2
        a--
        assertEquals(1, a)
    }
}