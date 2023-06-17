package com.example.mviexample.domain.repository

import com.example.mviexample.data.network.ApiResponse
import com.example.mviexample.data.network.model.CoinNameContainerDto

interface ApiRepository {

    suspend fun getCoinInfoList(): ApiResponse<CoinNameContainerDto>
}