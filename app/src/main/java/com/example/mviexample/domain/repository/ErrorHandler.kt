package com.example.mviexample.domain.repository

interface ErrorHandler {
    fun getErrorDescription(throwable: Throwable): String
}