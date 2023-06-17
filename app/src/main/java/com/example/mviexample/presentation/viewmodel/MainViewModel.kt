package com.example.mviexample.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.mviexample.domain.mvi.Action
import com.example.mviexample.domain.mvi.action.CoinAction
import com.example.mviexample.domain.mvi.result.GetTopCoinResult
import com.example.mviexample.domain.usecase.GetActionResultUseCase
import com.example.mviexample.presentation.AppLogger
import com.example.mviexample.presentation.LseState
import com.example.mviexample.presentation.coininfoscreen.state.CoinUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getActionResult: GetActionResultUseCase,
    logger: AppLogger,
) : BaseViewModel() {

    private val _coinUiState = MutableStateFlow(CoinUiState())
    val coinUiState: StateFlow<CoinUiState> = _coinUiState.asStateFlow()

    init {
        logger.connect(javaClass)
    }

    fun onAction(action: Action) {
        launch {
            renderGetCoinFragment(action)
        }
    }

    private suspend fun renderGetCoinFragment(action: Action) {
        when (action) {
            is CoinAction.GetTopCoinAction -> {
                getActionResult(action).map { result ->
                    when (result) {
                        is GetTopCoinResult.Success -> {
                            _coinUiState.update {
                                it.copy(
                                    getCoinListState = LseState.Success,
                                    coinNameList = result.getTopCoinsResponse?.coins?.map { it.name }
                                        ?: listOf()
                                )
                            }
                        }

                        is GetTopCoinResult.Failure -> {
                            _coinUiState.update {
                                it.copy(
                                    getCoinListState = LseState.Error(
                                        result.interpretedError?.userFriendlyInterpretation ?: ""
                                    )
                                )
                            }
                        }
                        is GetTopCoinResult.Loading -> {
                            _coinUiState.update {
                                it.copy(
                                    getCoinListState = LseState.Loading
                                )
                            }
                        }
                    }
                }.stateIn(viewModelScope)
            }
        }
    }
}