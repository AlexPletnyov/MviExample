package com.example.mviexample.data.error

interface ErrorHandler {
    fun getErrorDescription(throwable: Throwable): String
}