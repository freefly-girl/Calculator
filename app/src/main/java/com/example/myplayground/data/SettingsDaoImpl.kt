package com.example.myplayground.data

import android.content.SharedPreferences
import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.domain.entity.ForceVibrationTypeEnum
import com.example.myplayground.domain.entity.ForceVibrationTypeEnum.NO
import com.example.myplayground.domain.entity.FormatResultTypeEnum
import com.example.myplayground.domain.entity.FormatResultTypeEnum.MANY
import com.example.myplayground.domain.entity.FormatResultTypeEnum.valueOf
import com.example.myplayground.domain.entity.ResultPanelType
import com.example.myplayground.domain.entity.ResultPanelType.RIGHT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsDaoImpl(
  private val preferences: SharedPreferences
) : SettingsDao {

  override suspend fun getResultPanelType(): ResultPanelType = withContext(Dispatchers.IO) {
    preferences.getString(RESULT_PANEL_TYPE_KEY, null)
      ?.let { ResultPanelType.valueOf(it) } ?: RIGHT
  }

  override suspend fun setResultPanelType(resultPanelType: ResultPanelType) =
    withContext(Dispatchers.IO) {
      preferences.edit().putString(RESULT_PANEL_TYPE_KEY, resultPanelType.name).apply()
    }

  override suspend fun getFormatResultType(): FormatResultTypeEnum = withContext(Dispatchers.IO) {
    preferences.getString(FORMAT_RESULT_TYPE_KEY, null)
      ?.let { valueOf(it) } ?: MANY
  }

  override suspend fun setFormatResultType(formatResultType: FormatResultTypeEnum) =
    withContext(Dispatchers.IO) {
      preferences.edit().putString(FORMAT_RESULT_TYPE_KEY, formatResultType.name).apply()
    }

  override suspend fun getForceVibrationType(): ForceVibrationTypeEnum =
    withContext(Dispatchers.IO) {
      preferences.getString(FORCE_VIBRATION_TYPE_KEY, null)
        ?.let { ForceVibrationTypeEnum.valueOf(it) } ?: NO
    }

  override suspend fun setForceVibrationType(forceVibrationType: ForceVibrationTypeEnum) =
    withContext(Dispatchers.IO) {
      preferences.edit().putString(FORCE_VIBRATION_TYPE_KEY, forceVibrationType.name).apply()
    }

  companion object {

    private const val RESULT_PANEL_TYPE_KEY = "RESULT_PANEL_TYPE_KEY"
    private const val FORMAT_RESULT_TYPE_KEY = "FORMAT_RESULT_TYPE_KEY"
    private const val FORCE_VIBRATION_TYPE_KEY = "FORCE_VIBRATION_TYPE_KEY"
  }
}

//    ? - функция выполнится только если значение не null
