package com.example.myplayground.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayground.data.calculateExpression
import com.example.myplayground.domain.SettingsDao
import com.example.myplayground.domain.entity.HistoryItem
import com.example.myplayground.domain.entity.HistoryRepository
import com.example.myplayground.domain.entity.ResultPanelType
import kotlinx.coroutines.launch

class MainViewModel(
  private val settingsDao: SettingsDao,
  private val historyRepository: HistoryRepository
) : ViewModel() {

  private var expression: String = ""

  private val _expressionState = MutableLiveData(ExpressionState(expression, 0))
  val expressionState: LiveData<ExpressionState> = _expressionState

  private val _resultState = MutableLiveData<String>()
  val resultState: LiveData<String> = _resultState

  private val _resultPanelState = MutableLiveData<ResultPanelType>()
  val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

  init {
    viewModelScope.launch {
      _resultPanelState.value = settingsDao.getResultPanelType()
    }
  }

  fun onEqualsClicked() {
    val result = calculateExpression(expression)
    viewModelScope.launch {
      historyRepository.add(HistoryItem(expression, result))
    }
    _resultState.value = result
    _expressionState.value = ExpressionState(result, result.length)
    expression = result
  }

  fun onNumberClick(number: Int, selection: Int) {
    expression = putInSelection(number.toString(), selection)
    _expressionState.value = ExpressionState(expression, selection + 1)
    _resultState.value = calculateExpression(expression)
  }

  fun onBackSpaceClicked(selection: Int) {
    if (expression.isNotBlank() && (expression.last().isDigit() || expression.endsWith("."))) {
      expression = expression.removeRange(selection - 1, selection)
      _expressionState.value = ExpressionState(expression, (selection - 1).coerceAtLeast(0))
      _resultState.value = calculateExpression(expression)
    }
  }

  fun onDotClicked(selection: Int) {
    if (expression.isNotBlank() && (expression.last().isDigit() || expression.endsWith("."))) {
      expression = putInSelection(".", selection)
      _expressionState.value = ExpressionState(expression, selection + 1)
      _resultState.value = calculateExpression(expression)
    }
  }

  fun onOperatorClicker(operator: Operator, selection: Int) {
    if (expression.isNotBlank() && (expression.last().isDigit() || expression.endsWith("."))) {
      expression = putInSelection(operator.symbol, selection)
      _expressionState.value = ExpressionState(expression, selection + 1)
      _resultState.value = calculateExpression(expression)
    }
  }

  fun onClearClicked(selectionStart: Int) {
    expression = ""
    _expressionState.value = ExpressionState("", 0)
    _resultState.value = expression
  }

  /**
   * put: что мы вставляем
   * selection: куда мы это вставляем
   */
  private fun putInSelection(put: String, selection: Int): String {
    return expression.substring(0, selection) +
            put +
            expression.substring(selection, expression.length)
  }

  override fun onCleared() {
    super.onCleared()
    Log.d("MainViewModel", "onCleared")
  }

  fun onStart() {
    viewModelScope.launch {
      _resultPanelState.value = settingsDao.getResultPanelType()
    }
  }

  fun onHistoryResult(item: HistoryItem?) {
    if (item != null) {
      expression = item.expression
      _expressionState.value = ExpressionState(expression, expression.length)
      _resultState.value = item.result
    }
  }
}

