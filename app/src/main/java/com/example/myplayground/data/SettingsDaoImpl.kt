package com.example.myplayground.data

import android.content.SharedPreferences
import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.domain.entity.FormatResultTypeEnum
import com.example.myplayground.domain.entity.ResultPanelType
import com.example.myplayground.domain.entity.ResultPanelType.LEFT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsDaoImpl(
  private val preferences: SharedPreferences
) : SettingsDao {

  override suspend fun getResultPanelType(): ResultPanelType = withContext(Dispatchers.IO) {
    preferences.getString(RESULT_PANEL_TYPE_KEY, null)
      ?.let { ResultPanelType.valueOf(it) } ?: LEFT
  }

  override suspend fun setResultPanelType(resultPanelType: ResultPanelType) =
    withContext(Dispatchers.IO) {
      preferences.edit().putString(RESULT_PANEL_TYPE_KEY, resultPanelType.name).apply()
    }

  override suspend fun getFormatResultType(): FormatResultTypeEnum = withContext(Dispatchers.IO) {
    preferences.getString(FORMAT_RESULT_TYPE_KEY, null)
      ?.let { FormatResultTypeEnum.valueOf(it) } ?: FormatResultTypeEnum.MANY
  }

  override suspend fun setFormatResultType(formatResultType: FormatResultTypeEnum) =
    withContext(Dispatchers.IO) {
      preferences.edit().putString(FORMAT_RESULT_TYPE_KEY, formatResultType.name).apply()
    }

  companion object {

    private const val RESULT_PANEL_TYPE_KEY = "RESULT_PANEL_TYPE_KEY"
    private const val FORMAT_RESULT_TYPE_KEY = "FORMAT_RESULT_TYPE_KEY"
  }
}

//    ? - функция выполнится только если значение не null
