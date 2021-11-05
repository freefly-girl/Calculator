package com.example.myplayground.di

import android.content.Context
import com.example.myplayground.data.HistoryRepositoryImpl
import com.example.myplayground.domain.entity.HistoryRepository

object HistoryRepositoryProvider {

  private var repository: HistoryRepository? = null

  fun get(context: Context): HistoryRepository =
    repository ?: HistoryRepositoryImpl(DatabaseProvider.get(context).historyItemDao)
      .also { repository = it }
}