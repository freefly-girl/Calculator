package com.example.myplayground

//ShortCuts:
// shift + cmd + R  ->  Run
// alt + enter  ->  подсказки
// alt  ->  выделяем несколько строк по первой позиции
// ctrl + /  -> делаем комментарий

fun main() {
    println("Hello, world!")

    var b = 2 // var - изменяемая
    val a: Float = 1f // val - неизменяемая
    val c: Double = 1.7
    val char: Char = 'c' // символ только в одинарных ковычках
    val string: String = "Hello World" // строки ВСЕГДА в двойных ковычках

//    b = 3
//    println(b)

    val name = "Anastasia"
    println("Hello $name !")
    println(test(2, 4)) // названия переменных добавляются после введения их значений

    println(weightToString(3500))
    println(weightToString(700))

    println(getNameOs(OsType.LINUX))
    println(getNameOs(OsType.WINDOWS))
}

fun test(a: Int, b: Int): Int {
    return a + b

//  fun test(a: Int, b: Int): Int = a + b эквивалентная запись
}

/**
 * Возвращать вес с единицей измерений
 * Если [weightGram] больше чем 1000
 * возвращаем в килограммах
 * иначе в граммах
 * в конце добавляется название единицы измерений
 */

//    val result: String = if (true) "sth" else "sth else"

fun weightToString(weightGram: Int): String =
    if (weightGram > 1000) {
        val weightKg = weightGram / 1000.0
        "$weightKg kg"
    } else {
        "$weightGram gram"
    }

fun getNameOs(typeOs: OsType): String {
    return when (typeOs) {
        OsType.WINDOWS -> "Windows"
        OsType.LINUX -> "Linux"
        OsType.MACOS -> "Mac OS"
        OsType.XBOX -> "XBOX"
    }
    /**
     * 1 -> "Windows"
     * 2 -> "Linux"
     * else -> "Mac OS"
     */
}

enum class OsType {
    WINDOWS,
    LINUX,
    MACOS,
    XBOX;
}



