package com.example.mviexample.presentation.screen

import androidx.lifecycle.ViewModel
import com.example.mviexample.domain.usecase.GetTopCoinsUseCase
import com.example.mviexample.presentation.common.AppLogger
import com.example.mviexample.presentation.common.UiStatus
import com.example.mviexample.data.error.ErrorHandler
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

class TopCoinsViewModel @Inject constructor(
    private val getTopCoinsUseCase: GetTopCoinsUseCase,
    private val errorHandler: ErrorHandler,
    logger: AppLogger,
) : ContainerHost<TopCoinsState, TopCoinsSideEffect>, ViewModel() {
    override val container = container<TopCoinsState, TopCoinsSideEffect>(
        TopCoinsState()
    )

    init {
        logger.connect(javaClass)
    }

    fun event(event: TopCoinsEvent) {
        when(event) {
            is TopCoinsEvent.GetTopCoins -> getTopCoins()
        }
    }

    private fun getTopCoins() {
        intent {
            reduce { state.copy(status = UiStatus.Loading) }
            getTopCoinsUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            status = UiStatus.Success,
                            coinNameList = it.coins.map { it.name }
                        )
                    }
                    postSideEffect(TopCoinsSideEffect.ShowToast("Success!"))
                }
                .onFailure {
                    reduce { state.copy(status = UiStatus.Error(errorHandler.getErrorDescription(it))) }
                }
        }
    }
}