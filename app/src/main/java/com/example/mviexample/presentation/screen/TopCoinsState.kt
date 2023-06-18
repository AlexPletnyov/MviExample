package com.example.mviexample.presentation.screen

import com.example.mviexample.presentation.common.UiStatus

data class TopCoinsState(
    val status: UiStatus = UiStatus.Initial,
    val coinNameList: List<String> = listOf()
)