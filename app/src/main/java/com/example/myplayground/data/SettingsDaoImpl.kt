package com.example.myplayground.data

import android.content.SharedPreferences
import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.domain.entity.ResultPanelType
import com.example.myplayground.domain.entity.ResultPanelType.LEFT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsDaoImpl(
  private val preferences: SharedPreferences
) : SettingsDao {

  override suspend fun setResultPanelType(resultPanelType: ResultPanelType) =
    withContext(Dispatchers.IO) {
      preferences.edit().putString(RESULT_PANEL_TYPE_KEY, resultPanelType.name).apply()
    }

  override suspend fun getResultPanelType(): ResultPanelType = withContext(Dispatchers.IO) {
    preferences.getString(RESULT_PANEL_TYPE_KEY, null)
      ?.let { ResultPanelType.valueOf(it) } ?: LEFT
  }
//    ? - функция выполнится только если значение не null

  companion object {

    private const val RESULT_PANEL_TYPE_KEY = "RESULT_PANEL_TYPE_KEY"
  }
}

