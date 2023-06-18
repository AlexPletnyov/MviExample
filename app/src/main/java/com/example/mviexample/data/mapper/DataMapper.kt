package com.example.mviexample.data.mapper

import com.example.mviexample.data.network.model.CoinDataDto
import com.example.mviexample.domain.model.CoinInfo
import com.example.mviexample.domain.model.GetTopCoinsResponse
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun mapCoinDataDtoToGetTopCoinsResponse(coinDataDto: CoinDataDto) =
        GetTopCoinsResponse(
            coins = coinDataDto.names?.map { CoinInfo(it.coinName?.name ?: "") } ?: listOf()
        )
}