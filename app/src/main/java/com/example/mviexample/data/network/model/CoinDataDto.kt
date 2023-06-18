package com.example.mviexample.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinDataDto(
    @SerializedName("Data")
    @Expose
    val names: List<CoinInfoDto>?
)