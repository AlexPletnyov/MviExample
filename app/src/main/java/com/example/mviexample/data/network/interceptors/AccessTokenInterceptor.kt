package com.example.mviexample.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response


class AccessTokenInterceptor :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = "9614a868c8adf9328962e9729a762c35bc1801cd28e37bd27c64d5e8e65fed7b"
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("apy_key", apiKey)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}