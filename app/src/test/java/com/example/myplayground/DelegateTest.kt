package com.example.myplayground

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

val object1 by lazy {
    println("Initialize")
    Class1(1, 2, 3, 4)
}

val test = run {
    println("Initialize run")
    1
}

fun main() {
    println("Start")

    val a = 1213123
    println(a)

    println(object1.b)
    println(object1.a)
    println(object1.c)

    val int: Int = 1
    int.also { print(it) }
}

class LazyDelegate<T>(
    private val initialize: () -> T
) : ReadOnlyProperty<Any?, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: initialize().also { value = it }
    }

}