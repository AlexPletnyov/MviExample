package com.example.mviexample.di.module

import com.example.mviexample.data.mapper.DataMapper
import com.example.mviexample.data.network.ApiService
import com.example.mviexample.data.network.Config
import com.example.mviexample.data.network.interceptors.AccessTokenInterceptor
import com.example.mviexample.data.repository.ApiRepositoryImpl
import com.example.mviexample.di.scopes.ApplicationScope
import com.example.mviexample.domain.repository.ApiRepository
import com.example.mviexample.presentation.common.AppLogger
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun providesOkHttpClient(
        logger: AppLogger
    ): OkHttpClient {
        logger.connect(javaClass)
        val logging =
            HttpLoggingInterceptor { message -> logger.d(message) }
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(AccessTokenInterceptor())
            .addNetworkInterceptor(logging)
            .build()

    }

    @Provides
    @ApplicationScope
    fun providesGson(): Gson = Gson()

    @Provides
    @ApplicationScope
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @ApplicationScope
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl(Config.API_HOST)
        .client(okHttpClient)
        .build()

    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @ApplicationScope
    fun provideApiRepository(
        apiService: ApiService,
        dataMapper: DataMapper
    ): ApiRepository = ApiRepositoryImpl(apiService, dataMapper)
}