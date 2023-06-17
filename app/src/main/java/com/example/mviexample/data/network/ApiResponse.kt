package com.example.mviexample.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiResponse<T : Any> {
    @SerializedName("Message")
    @Expose
    var message: String? = null

    @SerializedName("Data")
    @Expose
    var data: List<T>? = null
}