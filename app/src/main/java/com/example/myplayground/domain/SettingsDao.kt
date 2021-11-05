package com.example.myplayground.domain

import com.example.myplayground.domain.entity.FormatResultTypeEnum
import com.example.myplayground.domain.entity.ResultPanelType

interface SettingsDao {

  // suspend - функция может быть долгой, можно запускать только через Coroutine
  /**
   *  устанавливает тип отображения панели результата
   */
  suspend fun getResultPanelType(): ResultPanelType

  /**
   *  получает тип отображения панели результата
   */
  suspend fun setResultPanelType(resultPanelType: ResultPanelType)

  suspend fun getFormatResultType(): FormatResultTypeEnum
  suspend fun setFormatResultType(formatResultType: FormatResultTypeEnum)

  // TODO: 05.11.2021 Vibration
//  suspend fun getForceVibrationType(): ForceVibrationTypeEnum
//  suspend fun setForceVibrationType(forceVibrationType: ForceVibrationTypeEnum)

}
