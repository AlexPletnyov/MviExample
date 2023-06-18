package com.example.mviexample.presentation.mvi

interface Reducer<STATE, EVENT, EFFECT> {
    fun reduce(state: STATE, event: EVENT): Reduced<STATE?, EFFECT?>
    operator fun invoke(state: STATE, event: EVENT) = reduce(state, event)
}