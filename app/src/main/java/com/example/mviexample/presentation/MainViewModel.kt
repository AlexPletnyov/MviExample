package com.example.mviexample.presentation

import androidx.lifecycle.viewModelScope
import com.example.mviexample.domain.mvi.Action
import com.example.mviexample.domain.mvi.action.CoinAction
import com.example.mviexample.domain.mvi.result.GetTopCoinResult
import com.example.mviexample.domain.repository.PreferencesRepository
import com.example.mviexample.domain.usecase.GetActionResultUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel  @Inject constructor(
    private val getActionResult: GetActionResultUseCase,
    private val preferencesRepository: PreferencesRepository,
    private val logger: AppLogger,
) : BaseViewModel() {

    init {
        logger.connect(javaClass)
    }

    fun onAction (action: Action) {
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
                            logger.d(result.getTopCoinsResponse?.coins.toString())
                        }
                        is GetTopCoinResult.Failure -> {
                            logger.e(result.interpretedError?.userFriendlyInterpretation ?: "")
                        }
                        is GetTopCoinResult.Loading -> {

                        }
                    }
                }.stateIn(viewModelScope)
            }
        }
    }
}