package com.example.mviexample.domain.mvi.result

import com.example.mviexample.data.error.InterpretedError
import com.example.mviexample.domain.model.GetTopCoinsResponse
import com.example.mviexample.domain.mvi.Result

sealed class GetTopCoinResult : Result {
    object Loading : GetTopCoinResult()
    data class Success(val getTopCoinsResponse: GetTopCoinsResponse? = null) : GetTopCoinResult()
    data class Failure(val interpretedError: InterpretedError? = null) : GetTopCoinResult()
}