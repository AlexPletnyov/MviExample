package com.example.mviexample.data.repository

import com.example.mviexample.data.network.ApiResponse
import com.example.mviexample.data.network.ApiService
import com.example.mviexample.data.network.model.CoinNameContainerDto
import com.example.mviexample.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    override suspend fun getCoinInfoList(): ApiResponse<CoinNameContainerDto> {
        return apiService.getTopCoinsInfo()
    }
}