package com.example.mviexample.presentation.common

sealed class UiStatus {
    object Initial : UiStatus()
    object Loading : UiStatus()
    object Success : UiStatus()
    data class Error(val errorDescription: String) : UiStatus()
}