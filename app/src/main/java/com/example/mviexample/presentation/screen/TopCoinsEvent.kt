package com.example.mviexample.presentation.screen

sealed class TopCoinsEvent {
    object GetTopCoins : TopCoinsEvent()
}