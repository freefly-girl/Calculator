package com.example.myplayground.di

import android.content.Context
import com.example.myplayground.data.SettingsDaoImpl
import com.example.myplayground.domain.SettingsDao

object SettingsDaoProvider {

  private var dao: SettingsDao? = null

  fun getDao(context: Context): SettingsDao {
    return dao ?: SettingsDaoImpl(
      context.getSharedPreferences(
        "settings",
        Context.MODE_PRIVATE
      )
    ).also {
      dao = it
    }
  }
}