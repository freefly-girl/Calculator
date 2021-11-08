package com.example.myplayground.presentation.main

import android.content.Context
import android.content.Intent
import android.os.*
import android.view.Gravity
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myplayground.R
import com.example.myplayground.databinding.MainActivityBinding
import com.example.myplayground.di.HistoryRepositoryProvider
import com.example.myplayground.di.SettingsDaoProvider
import com.example.myplayground.domain.entity.ForceVibrationTypeEnum.*
import com.example.myplayground.domain.entity.ResultPanelType.*
import com.example.myplayground.presentation.common.BaseActivity
import com.example.myplayground.presentation.history.HistoryResult
import com.example.myplayground.presentation.main.Operator.*
import com.example.myplayground.presentation.settings.SettingsActivity

// ctrl + P -> показывает параметры функции
// зажать ctrl и навести мышь на функцию -> увидим ее источник, документацию
// зажимаем ctrl + shift, теперь при помощи клавиш вверх/вниз можем перемещать строку,
// на которой в этот момент стоит мышка

class MainActivity : BaseActivity() {

  private val viewBinding by viewBinding(MainActivityBinding::bind)
  private val viewModel by viewModels<MainViewModel> {
    // создаем анонимный класс
    object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
          SettingsDaoProvider.getDao(this@MainActivity),
          HistoryRepositoryProvider.get(this@MainActivity)
        ) as T
      }
    }
  }

  private val resultLauncher = registerForActivityResult(HistoryResult()) { item ->
    viewModel.onHistoryResult(item)
  }

  private var vibrationEffect: VibrationEffect? = null

  //  @RequiresApi(31)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//    val vibrator = vibratorManager.defaultVibrator

    viewBinding.mainInput.apply { showSoftInputOnFocus = false }

    viewBinding.mainActivitySettings.setOnClickListener { openSettings() }

    viewBinding.mainHistory?.setOnClickListener { openHistory() }

    viewModel.expressionState.observe(this) {
      viewBinding.mainInput.setText(it.expression)
      viewBinding.mainInput.setSelection(it.selection)
    }

    viewModel.resultState.observe(this) {
      viewBinding.mainResult.text = it
    }


    listOf(
      viewBinding.mainZero,
      viewBinding.mainOne,
      viewBinding.mainTwo,
      viewBinding.mainThree,
      viewBinding.mainFour,
      viewBinding.mainFive,
      viewBinding.mainSix,
      viewBinding.mainSeven,
      viewBinding.mainEight,
      viewBinding.mainNine
    ).forEachIndexed { index, textView ->
      textView.setOnClickListener {

        // TODO: 07.11.2021 Vibration
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect)
        viewModel.onNumberClick(index, viewBinding.mainInput.selectionStart)
      }
    }


    viewBinding.mainBack.setOnClickListener {
      viewModel.onBackSpaceClicked(viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainClear.setOnClickListener {
      viewModel.onClearClicked(viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainPoint.setOnClickListener {
      viewModel.onDotClicked(viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainPlus.setOnClickListener {
      viewModel.onOperatorClicker(PLUS, viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainMinus.setOnClickListener {
      viewModel.onOperatorClicker(MINUS, viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainDivider.setOnClickListener {
      viewModel.onOperatorClicker(DIVIDE, viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainMultiple.setOnClickListener {
      viewModel.onOperatorClicker(MULTIPLE, viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainDergee?.setOnClickListener {
      viewModel.onOperatorClicker(DEGREE, viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainSqrt?.setOnClickListener {
      viewModel.onSqrtButtonClick()
    }

    viewBinding.mainEquals.setOnClickListener { viewModel.onEqualsClicked() }


    viewModel.resultPanelState.observe(this) {
      with(viewBinding.mainResult) {
        gravity = when (it) {
          LEFT -> Gravity.START.or(Gravity.CENTER_VERTICAL)
          RIGHT -> Gravity.END.or(Gravity.CENTER_VERTICAL)
          HIDE -> viewBinding.mainResult.gravity
        }
        isVisible = it != HIDE
      }
    }

    viewModel.forceVibrationState.observe(this) {
      vibrationEffect = when (it) {
        NO -> VibrationEffect.createOneShot(0, VibrationEffect.DEFAULT_AMPLITUDE)
        SMALL -> VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
        MEDIUM -> VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK)
        STRONG -> VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
      }
    }
  }

  override fun onStart() {
    super.onStart()
    viewModel.onStart()
  }

  private fun openSettings() {
    startActivity(Intent(this, SettingsActivity::class.java))
  }

  private fun openHistory() {
    resultLauncher.launch()
  }
}