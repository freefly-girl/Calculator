package com.example.myplayground

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            launch {
                timer(20_000)
            }
        }
    }
    println("Time $time")
}

private suspend fun makeComputaion(): Int = coroutineScope {
    val result1: Deferred<Int> = async { computation1() }
    val result2: Deferred<Int> = async { computation2() }
    result1.await() + result2.await()
}

suspend fun computation1(): Int {
    delay(2000L)
    return 2
}

suspend fun computation2(): Int {
    delay(3000L)
    return 3
}


private suspend fun doThread() = coroutineScope {
    repeat(100_000) {
        launch {
            delay(2000)
            println(";")
        }
    }
}

private suspend fun doWorld() {
    coroutineScope {
        val job: Job = launch {
            delay(2000)
            println("World 1 !")
        }
//        job.cancel()
        job.join()
        println(job.isCompleted)
        launch {
            delay(4000)
            println("World 2 !")
        }
        println("Hello")
    }
}


// данный код также прописывается в main для вывода удвоенного "test" -> testtest
//    println("test".double())
//
//    val action: String.() -> Unit = {
//        double()
//        length
//    }
//    "test".action()
//
//object StringUnit {
//    fun double(string: String) = string + string
//}
//
//fun String.double() = this + this

/**
 * Первые 2 секунды принтить время каждые 0.1 секунды
 * До 10 секунд принтить каждую секунду
 * Далее пинтить каждые 2 секунды
 * @param time время работы таймера
 */

private suspend fun timer(time: Long) {
    var delayedTime = 0
    while (delayedTime < time) {
        val deltaDelay = when {
            delayedTime < 2_000 -> 100
            delayedTime < 10_000 -> 1000
            else -> 2000
        }
        delay(deltaDelay.toLong())
        delayedTime += deltaDelay
        println(delayedTime)
    }
}