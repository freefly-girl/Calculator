package com.example.myplayground.examples

fun main() {
  val feedback = listOf(
    Feedback(2, null),
    Feedback(null, "Всё супер!"),
    Feedback(4, "No offline mode"),
    Feedback(3, null),
    Feedback(1, null),
    Feedback(2, null),
    Feedback(5, null),
  )

  val rating: Int? = 0
  rating ?: 0

  println(averageRating(feedback))
}

fun averageRating(feedback: List<Feedback>): Float {
  val rateCount =
    feedback.count { it.rating != null } // считает кол-во фидбэков с ненулевым рейтингом
  return ((feedback.sumOf { it.rating ?: 0 }).toFloat() / rateCount.toFloat())
}

data class Feedback(
  val rating: Int?, // рейтинг может отсутствовать, как и комментарий (т.е. быть равным null)
  val comment: String?
)