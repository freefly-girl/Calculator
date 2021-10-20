package com.example.myplayground

fun main() {
    val object1 = Class1(1, 2, 3, 4)
//    println(Class1(1) == Class1(1))
//    val object2 = object1.copy(c = 10)

    val map = mapOf("Max" to 3)
    val age = map["Max"]

    println(object1)

}

data class Class1(
    val a: Int,
    val b: Int,
    val c: Int,
    val d: Int
)