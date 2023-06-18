package com.example.mviexample.presentation.screen

sealed class TopCoinsSideEffect {
    class ShowToast(val message: String): TopCoinsSideEffect()
}