package com.example.myplayground.data

import com.example.myplayground.data.db.history.HistoryItemDao
import com.example.myplayground.data.db.history.HistoryItemEntity
import com.example.myplayground.domain.entity.HistoryItem
import com.example.myplayground.domain.entity.HistoryRepository

class HistoryRepositoryImpl(
  private val historyItemDao: HistoryItemDao
) : HistoryRepository {

  override suspend fun add(historyItem: HistoryItem) {
    historyItemDao.insert(historyItem.toHistoryItemEntity())
  }

  override suspend fun getAll(): List<HistoryItem> =
    historyItemDao.getAll().map { it.toHistoryItem() }.sortedByDescending { it.createdAt }

  private fun HistoryItem.toHistoryItemEntity() = HistoryItemEntity(
    id = 0,
    expression = expression,
    result = result,
    createdAt = createdAt
  )

  private fun HistoryItemEntity.toHistoryItem() = HistoryItem(
    expression = expression,
    result = result,
    createdAt = createdAt
  )
}