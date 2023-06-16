package com.example.mviexample.data.mapper

import com.example.mviexample.data.network.model.CoinNamesListDto
import com.example.mviexample.domain.model.CoinInfo
import com.example.mviexample.domain.model.GetTopCoinsResponse

class DataMapper {

    fun mapCoinNamesListDtoToGetTopCoinsResponse(coinNamesListDto: CoinNamesListDto) =
        GetTopCoinsResponse(
            coins = coinNamesListDto.names?.map { CoinInfo(it.coinName?.name ?: "") } ?: listOf()
        )
}