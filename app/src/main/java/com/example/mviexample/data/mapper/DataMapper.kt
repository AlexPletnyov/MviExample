package com.example.mviexample.data.mapper

import com.example.mviexample.data.network.model.CoinNameContainerDto
import com.example.mviexample.domain.model.CoinInfo
import com.example.mviexample.domain.model.GetTopCoinsResponse
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun mapCoinNamesListDtoToGetTopCoinsResponse(coinNamesListDto: List<CoinNameContainerDto>) =
        GetTopCoinsResponse(
            coins = coinNamesListDto.map { CoinInfo(it.coinName?.name ?: "") }
        )
}