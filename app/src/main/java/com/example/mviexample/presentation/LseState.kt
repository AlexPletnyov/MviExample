package com.example.mviexample.presentation

sealed class LseState {
    object Initial : LseState()
    object Loading : LseState()
    object Success : LseState()
    data class Status(val status: String) : LseState()
    data class Error(val errorDescription: String) : LseState()
}