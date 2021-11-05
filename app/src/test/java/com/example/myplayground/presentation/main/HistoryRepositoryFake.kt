package com.example.myplayground.presentation.main

import com.example.myplayground.domain.entity.HistoryItem
import com.example.myplayground.domain.entity.HistoryRepository

class HistoryRepositoryFake : HistoryRepository {

  override suspend fun add(historyItem: HistoryItem) {

  }

  override suspend fun getAll(): List<HistoryItem> {
    return emptyList()
  }
}