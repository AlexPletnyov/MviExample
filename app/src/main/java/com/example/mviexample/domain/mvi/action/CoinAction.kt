package com.example.mviexample.domain.mvi.action

import com.example.mviexample.domain.mvi.Action

sealed class CoinAction: Action {
    object GetTopCoinAction : CoinAction()
}