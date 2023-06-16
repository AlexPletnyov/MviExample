package com.example.mviexample.domain.repository

import androidx.lifecycle.LiveData
import com.example.mviexample.data.network.ApiResponse
import com.example.mviexample.data.network.model.CoinNamesListDto

interface ApiRepository {

    suspend fun getCoinInfoList(): ApiResponse<CoinNamesListDto>
}