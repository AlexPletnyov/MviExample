package com.example.mviexample.domain.repository

import com.example.mviexample.domain.model.GetTopCoinsResponse

interface ApiRepository {

    suspend fun getCoinInfoList(): GetTopCoinsResponse
}