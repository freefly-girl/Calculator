package com.example.myplayground

fun main() {
    val animals = listOf(
        Cat(1, 2),
        Cat(10, 10),
        Cat(15, 10)
    )

    for (animal in animals) {
//        println(animal.age)
        println(animal.weight)
    }

    for (i in animals.indices) {
        println(animals[i].weight)
    }

    val forEachAnimal: (animal: Animal) -> Unit = { animal ->
        println(animal.weight)
    }

    val forEachAnimal2: (cat: Cat, dog: Dog) -> Unit = { cat: Cat, dog: Dog ->

    }

    forEachAnimal2(Cat(1, 5), Dog(3, 7))
    animals.forEach(forEachAnimal)
    animals.forEach(::forEachAnimalFunc)

    animals.forEach { println(it.weight) }

    println(getSumAge(animals)) // 26 = 1 + 10 + 15
    println(getSumAge2(animals))

//    val cats = animals.filter { it is Cat }
    val cats = animals.filterIsInstance<Cat>()

    val fatAnimals = animals.filter { it.weight > 5 }
    val fatAnimal = animals.first { it.weight > 5 }
    animals.maxOf { it.weight }
    animals.sumOf { it.weight }

    var count: Int = 10
    while (count > 0) {
        println("Count = $count")
        count--
    }

}

fun forEachAnimalFunc(animal: Animal) {
    println(animal.weight)
}

//todo задачка №1
fun getSumAge(animals: List<Animal>): Int {

    var res: Int = 0
    animals.forEach {
        res += it.age
    }
    return res
}

fun getSumAge2(animals: List<Animal>): Int =
    animals.sumOf { it.age }

