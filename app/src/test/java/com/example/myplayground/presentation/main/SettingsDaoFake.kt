package com.example.myplayground.presentation.main

import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.domain.entity.ResultPanelType

class SettingsDaoFake : SettingsDao {

  private var resultPanelType: ResultPanelType = ResultPanelType.LEFT

  override suspend fun setResultPanelType(resultPanelType: ResultPanelType) {
    this.resultPanelType = resultPanelType
  }

  override suspend fun getResultPanelType(): ResultPanelType {
    return resultPanelType
  }
}

