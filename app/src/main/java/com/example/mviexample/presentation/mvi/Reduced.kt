package com.example.mviexample.presentation.mvi

data class Reduced<STATE, EFFECT>(
    val state: STATE,
    val effect: EFFECT
)