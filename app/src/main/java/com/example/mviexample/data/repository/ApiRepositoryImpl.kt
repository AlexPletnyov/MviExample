package com.example.mviexample.data.repository

import com.example.mviexample.data.network.ApiResponse
import com.example.mviexample.data.network.ApiService
import com.example.mviexample.data.network.model.CoinNamesListDto
import com.example.mviexample.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    override suspend fun getCoinInfoList(): ApiResponse<CoinNamesListDto> {
        return apiService.getTopCoinsInfo()
    }
}