package com.example.mviexample.presentation.screen

import com.example.mviexample.domain.model.GetTopCoinsResponse

sealed class TopCoinsEvent {
    object GetTopCoins : TopCoinsEvent()
    class GetTopCoinsSuccess(val getTopCoinsResponse: GetTopCoinsResponse) : TopCoinsEvent()
    class GetTopCoinsFailure(val interpretedError: String) : TopCoinsEvent()
}