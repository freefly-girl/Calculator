package com.example.myplayground.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.presentation.main.Operator.DIVIDE
import com.example.myplayground.presentation.main.Operator.PLUS
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()
  private val settingsDao: SettingsDao = SettingsDaoFake()
  private val historyRepository = HistoryRepositoryFake()

  @Test
  fun testPlus() {
    val viewModel = MainViewModel(settingsDao, historyRepository)

    viewModel.onNumberClick(2, 0)
    viewModel.onOperatorClicker(PLUS, 1)
    viewModel.onNumberClick(2, 2)

    Assert.assertEquals("2+2", viewModel.expressionState.value?.expression)
      Assert.assertEquals("4", viewModel.formatResultState.value)
  }

  @Test
  fun testAsDivide() {
    val viewModel = MainViewModel(settingsDao, historyRepository)

    // 10 / 2 -> 5
    // selection: место в строчке по порядку
    viewModel.onNumberClick(1, 0)
    viewModel.onNumberClick(0, 1)
    viewModel.onOperatorClicker(DIVIDE, 2)
    viewModel.onNumberClick(2, 3)

    Assert.assertEquals("10/2", viewModel.expressionState.value?.expression)
    Assert.assertEquals("5", viewModel.resultState.value)
  }

  @Test
  fun testEmpty() {
    val viewModel = MainViewModel(settingsDao, historyRepository)

    viewModel.onOperatorClicker(DIVIDE, 0)

    Assert.assertEquals(
      viewModel.resultState.value?.isEmpty(),
      viewModel.resultState.value?.isEmpty()
    )
  }
}

