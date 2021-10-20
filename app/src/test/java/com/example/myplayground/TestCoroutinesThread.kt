package com.example.myplayground

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() {
    runBlocking {
        launch(Dispatchers.Default) { printCurrentThread() }
        launch(Dispatchers.IO) { printCurrentThread() }
//        launch(Dispatchers.Main) { printCurrentThread() }
        launch(Dispatchers.Unconfined) { printCurrentThread() }
    }
    ViewModel().apply {
        init()
        init()
        init()
        init()
        init()
        cancelAll() // World уже не выведет
    }

    Thread.sleep(4000) // задерживает основной поток на 4000ms, что позволяет пропечатать World
}


// launch(Dispatchers. ) - указывает выбранный поток
// .Default - дефолтный (кол-во потоков в зависимости от кол-ва ядер процессора)
// .IO - для работы с базами данных (кол-во поток ничем не ограничено)
// .Main - возвращает результат в главном потоке
// .Unconfined - (используется редко)

private fun printCurrentThread() {
    println(Thread.currentThread())
}

class ViewModel : CoroutineScope {

    override val coroutineContext: CoroutineContext = Job()

    fun init() {
        launch {
            delay(1000)
            println("World")
        }
        println("Hello")
    }

    fun cancelAll() {
        cancel()
    }
}
