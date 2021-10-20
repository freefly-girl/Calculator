package com.example.myplayground

fun main() {

    val animal: Animal = Cat(age = 1, weight = 2)
    animal.inner
    animal.printAgeAndWeight()
    animal.printAgeAndWeight("name")
    val run: Run = Cat(age = 1, weight = 2)

    val shape: Shape = Rect(2, 3)
    shape.perimetr()

    //как вызвать двух уток разного возраста через создание новой переменной
    val duck1 = Duck(10)
    val duck2 = Duck(2)
    duck1.age
    duck2.age
}

private fun test() {

}

abstract class Animal {
    abstract val age: Int
    abstract val weight: Int
    abstract val isAlive: Boolean

    internal var inner: Boolean = false

    fun printAgeAndWeight(name: String = "") {
        println("$age $weight")
    }

}

class Duck(val age: Int) {

}

class Dog(
    override val age: Int,
    override val weight: Int,
    override val isAlive: Boolean = true
) : Animal()

abstract class Test()
class Cat(
    override val age: Int,
    override val weight: Int,
    override val isAlive: Boolean = true
) : Animal(), Voice, Run {

    override fun makeVoice() {

    }

    override fun run() {

    }
}

interface Voice {
    fun makeVoice()
}

interface Run {
    fun run()
}


abstract class Shape {

    abstract fun perimetr(): Double
}

class Rect(val a: Int, val b: Int) : Shape() {

    override fun perimetr(): Double {
        return (2 * a + 2 * b).toDouble()
    }
}

class Circle(val diameter: Double) : Shape() {

    override fun perimetr(): Double = 2 * Math.PI * diameter
}
