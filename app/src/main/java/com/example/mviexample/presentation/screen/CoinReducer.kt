package com.example.mviexample.presentation.screen

import com.example.mviexample.presentation.mvi.Reduced
import com.example.mviexample.presentation.common.UiStatus
import com.example.mviexample.presentation.mvi.Reducer

class CoinReducer : Reducer<TopCoinsState, TopCoinsEvent, TopCoinsSideEffect> {
    override fun reduce(
        state: TopCoinsState,
        event: TopCoinsEvent
    ): Reduced<TopCoinsState?, TopCoinsSideEffect?> {
        var coinsState: TopCoinsState? = null
        var coinsSideEffect: TopCoinsSideEffect? = null

        when (event) {
            is TopCoinsEvent.GetTopCoins -> {
                coinsState = state.copy(status = UiStatus.Loading)
            }
            is TopCoinsEvent.GetTopCoinsSuccess -> {
                coinsState = state.copy(
                    status = UiStatus.Success,
                    coinNameList = event.getTopCoinsResponse.coins.map { it.name }
                )
                coinsSideEffect = TopCoinsSideEffect.ShowToast("Success!")
            }
            is TopCoinsEvent.GetTopCoinsFailure -> {
                coinsState = state.copy(
                    status = UiStatus.Error(event.interpretedError),
                )
                coinsSideEffect = TopCoinsSideEffect.ShowToast(event.interpretedError)
            }
        }
        return Reduced(coinsState, coinsSideEffect)
    }
}