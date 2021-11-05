package com.example.myplayground.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.domain.entity.FormatResultTypeEnum
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

  // TODO: 05.11.2021 Digits
  private val _formatResultState = MutableLiveData<FormatResultTypeEnum>(FormatResultTypeEnum.MANY)
  val formatResultState: LiveData<FormatResultTypeEnum> = _formatResultState

  private val _openFormatResultAction = SingleLiveEvent<FormatResultTypeEnum>()
  val openFormatResultAction: LiveData<FormatResultTypeEnum> = _openFormatResultAction


  fun onFormatResultChanged(formatResultType: FormatResultTypeEnum) {
    _formatResultState.value = formatResultType
    viewModelScope.launch {
      settingsDao.setFormatResultType(formatResultType)
    }
  }

  fun onFormatResultPanelTypeClicked() {
    _openFormatResultAction.value = _formatResultState.value
  }


  init {
    viewModelScope.launch {
      _resultPanelState.value = settingsDao.getResultPanelType()
      _formatResultState.value = settingsDao.getFormatResultType()
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

