package com.example.myplayground.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayground.domain.entity.HistoryItem
import com.example.myplayground.domain.entity.HistoryRepository
import com.example.myplayground.presentation.common.SingleLiveEvent
import kotlinx.coroutines.launch

class HistoryViewModel(
  private val historyRepository: HistoryRepository
) : ViewModel() {

  private val _historyItemState = MutableLiveData<List<HistoryItem>>()
  val historyItemState: LiveData<List<HistoryItem>> = _historyItemState

  private val _closeWithResult = SingleLiveEvent<HistoryItem>()
  val closeWithResult: LiveData<HistoryItem> = _closeWithResult

  init {
    viewModelScope.launch {
      _historyItemState.value = historyRepository.getAll()
    }
  }

  fun onItemClicked(historyItem: HistoryItem) {
    _closeWithResult.value = historyItem
  }
}
