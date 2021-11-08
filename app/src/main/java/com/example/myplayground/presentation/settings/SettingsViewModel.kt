package com.example.myplayground.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.domain.entity.ForceVibrationTypeEnum
import com.example.myplayground.domain.entity.ForceVibrationTypeEnum.SMALL
import com.example.myplayground.domain.entity.FormatResultTypeEnum
import com.example.myplayground.domain.entity.FormatResultTypeEnum.MANY
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


  private val _formatResultState = MutableLiveData<FormatResultTypeEnum>(MANY)
  val formatResultState: LiveData<FormatResultTypeEnum> = _formatResultState

  private val _openFormatResultAction = SingleLiveEvent<FormatResultTypeEnum>()
  val openFormatResultAction: LiveData<FormatResultTypeEnum> = _openFormatResultAction


  private val _forceVibrationState = MutableLiveData<ForceVibrationTypeEnum>(SMALL)
  val forceVibrationState: LiveData<ForceVibrationTypeEnum> = _forceVibrationState

  private val _openForceVibrationAction = SingleLiveEvent<ForceVibrationTypeEnum>()
  val openForceVibrationAction: LiveData<ForceVibrationTypeEnum> = _openForceVibrationAction


  init {
    viewModelScope.launch {
      _resultPanelState.value = settingsDao.getResultPanelType()
      _formatResultState.value = settingsDao.getFormatResultType()
      _forceVibrationState.value = settingsDao.getForceVibrationType()
    }
  }


  fun onFormatResultChanged(formatResultType: FormatResultTypeEnum) {
    _formatResultState.value = formatResultType
    viewModelScope.launch {
      settingsDao.setFormatResultType(formatResultType)
    }
  }

  fun onFormatResultPanelTypeClicked() {
    _openFormatResultAction.value = _formatResultState.value
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


  fun onForceVibrationChanged(forceVibrationType: ForceVibrationTypeEnum) {
    _forceVibrationState.value = forceVibrationType
    viewModelScope.launch {
      settingsDao.setForceVibrationType(forceVibrationType)
    }
  }

  fun onForceVibrationPanelTypeClicked() {
    _openForceVibrationAction.value = _forceVibrationState.value
  }
}

