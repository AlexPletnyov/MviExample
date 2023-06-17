package com.example.mviexample.presentation.coininfoscreen.state

import com.example.mviexample.presentation.LseState

data class CoinUiState(
    val getCoinListState: LseState = LseState.Initial,
    val coinNameList: List<String> = listOf()
)