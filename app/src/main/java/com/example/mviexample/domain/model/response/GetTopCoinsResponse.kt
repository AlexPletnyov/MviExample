package com.example.mviexample.domain.model.response

import com.example.mviexample.domain.model.CoinInfo

data class GetTopCoinsResponse(
    val coins: List<CoinInfo>
)