package com.example.mviexample.data.repository

import com.example.mviexample.data.mapper.DataMapper
import com.example.mviexample.data.network.ApiService
import com.example.mviexample.domain.model.GetTopCoinsResponse
import com.example.mviexample.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataMapper: DataMapper
) : ApiRepository {

    override suspend fun getCoinInfoList(): GetTopCoinsResponse {
        val response = apiService.getTopCoinsInfo()
        return dataMapper.mapCoinDataDtoToGetTopCoinsResponse(response)
    }
}