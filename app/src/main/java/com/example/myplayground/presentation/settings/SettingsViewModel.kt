package com.example.myplayground.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.domain.entity.ResultPanelType
import com.example.myplayground.domain.entity.ResultPanelType.LEFT
import com.example.myplayground.presentation.common.SingleLiveEvent
import kotlinx.coroutines.launch

class SettingsViewModel(
  private val settingsDao: SettingsDao
) : ViewModel() {

  private val _resultPanelState = MutableLiveData<ResultPanelType>(LEFT)
  val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

  private val _openResultPanelAction = SingleLiveEvent<ResultPanelType>()
  val openResultPanelAction: LiveData<ResultPanelType> = _openResultPanelAction

  init {
    viewModelScope.launch {
      _resultPanelState.value = settingsDao.getResultPanelType()
    }
  }

  fun onResultPanelTypeChanged(resultPanelType: ResultPanelType) {
    _resultPanelState.value = resultPanelType
    viewModelScope.launch {
      settingsDao.setResultPanelType(resultPanelType)
    }
  }

  fun onResultPanelTypeClicked() {
    _openResultPanelAction.value = _resultPanelState.value
  }
}

