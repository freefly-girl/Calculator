package com.example.myplayground.presentation.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myplayground.R
import com.example.myplayground.databinding.SettingsActivityBinding
import com.example.myplayground.di.SettingsDaoProvider
import com.example.myplayground.domain.entity.ResultPanelType
import com.example.myplayground.presentation.common.BaseActivity

class SettingsActivity : BaseActivity() {

  private val viewBinding by viewBinding(SettingsActivityBinding::bind)
  private val viewModel by viewModels<SettingsViewModel> {
    // создаем анонимный класс
    object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsViewModel(SettingsDaoProvider.getDao(this@SettingsActivity)) as T
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.settings_activity)
    viewBinding.settingsBack.setOnClickListener {
      finish()
    }

    viewBinding.resultPanelContainer.setOnClickListener {
      viewModel.onResultPanelTypeClicked()
    }

    viewModel.resultPanelState.observe(this) { state ->
      viewBinding.resultPanelDescription.text =
        resources.getStringArray(R.array.result_panel_types)[state.ordinal]
    }

    viewModel.openResultPanelAction.observe(this) { type ->
      showDialog(type)
    }
  }

  private fun showDialog(type: ResultPanelType) {
    var result: Int? = null
    AlertDialog.Builder(this)
      .setTitle("Title")
      .setPositiveButton("Ok") { dialog, id ->
        result?.let { viewModel.onResultPanelTypeChanged(ResultPanelType.values()[it]) }
      }
      .setNegativeButton("Отмена") { dialog, id ->

      }
      .setSingleChoiceItems(R.array.result_panel_types, type.ordinal) { dialog, id ->
        viewModel.onResultPanelTypeChanged(ResultPanelType.values()[id])

      }
      .show()
  }

  // не используется в калькуляторе
  private fun showCustomDialog(type: ResultPanelType) {
    val view = LayoutInflater.from(this).inflate(R.layout.settings_activity, null)
    val viewBinding = SettingsActivityBinding.bind(view)
    viewBinding.settingsBack.setOnClickListener {
      finish()
    }
    AlertDialog.Builder(this)
      .setView(view)
      .show()

  }

}