package com.example.mviexample.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiResponse<T : Any> {
    @SerializedName("result")
    @Expose
    var result: T? = null

    @SerializedName("error")
    @Expose
    var error: String? = null
}