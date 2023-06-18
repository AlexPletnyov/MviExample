package com.example.mviexample.presentation.mvi

interface MviView<STATE, EFFECT> {
    fun render(state: STATE)
    fun sideEffect(effect: EFFECT)
}