package com.example.mviexample.presentation.screen

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviexample.domain.repository.ErrorHandler
import com.example.mviexample.domain.usecase.GetTopCoinsUseCase
import com.example.mviexample.presentation.common.AppLogger
import com.example.mviexample.presentation.mvi.MviView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopCoinsViewModel @Inject constructor(
    private val getTopCoinsUseCase: GetTopCoinsUseCase,
    private val errorHandler: ErrorHandler,
    logger: AppLogger,
) : ViewModel() {

    private val coinState = MutableStateFlow(TopCoinsState())
    private val coinEffect = MutableSharedFlow<TopCoinsSideEffect>()
    private val coinEvent = MutableSharedFlow<TopCoinsEvent>()

    val coinReducer = CoinReducer()

    init {
        logger.connect(javaClass)
    }

    fun event(event: TopCoinsEvent) {
        viewModelScope.launch {
            coinEvent.emit(event)
            when (event) {
                is TopCoinsEvent.GetTopCoins -> getTopCoins()
                else -> {}
            }
        }
    }

    fun bind(
        lifecycleCoroutineScope: LifecycleCoroutineScope,
        mviView: MviView<TopCoinsState, TopCoinsSideEffect>
    ) {
        coinState
            .onEach {
                mviView.render(it)
            }
            .launchIn(lifecycleCoroutineScope)

        coinEffect
            .onEach {
                mviView.sideEffect(it)
            }
            .launchIn(lifecycleCoroutineScope)

        lifecycleCoroutineScope.launch {
            coinEvent.collect() { event ->
                val reduced = coinReducer(coinState.value, event)
                reduced.state?.let {
                    coinState.value = it
                }
                reduced.effect?.let {
                    coinEffect.emit(it)
                }
            }
        }
    }

    private suspend fun getTopCoins() {
        getTopCoinsUseCase()
            .onSuccess {
                event(TopCoinsEvent.GetTopCoinsSuccess(it))
            }
            .onFailure {
                event(TopCoinsEvent.GetTopCoinsFailure(errorHandler.getErrorDescription(it)))
            }
    }
}