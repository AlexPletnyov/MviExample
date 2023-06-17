package com.example.mviexample.domain.mvi

import android.util.Log
import com.example.mviexample.data.error.ApiErrorModel
import com.example.mviexample.data.error.MapErrorUtil.extractApiError
import com.example.mviexample.data.mapper.DataMapper
import com.example.mviexample.data.network.ApiResponse
import com.example.mviexample.domain.repository.ApiRepository
import com.example.mviexample.domain.repository.PreferencesRepository
import retrofit2.HttpException

abstract class Middleware<A, R>(
    val apiRepository: ApiRepository,
    val preferencesRepository: PreferencesRepository? = null,
    val mapper: DataMapper? = null,
) {
    abstract suspend fun result(action: A): R?
    suspend operator fun invoke(action: A) = result(action)

    suspend fun <T : Any> doRequest(
        requestAsync: suspend () -> ApiResponse<T>,
        responseOk: List<T>.() -> Unit,
        onApiErrorStatus: ApiErrorModel.() -> Unit,
        onException: Exception.() -> Unit
    ) {
        try {
            val response = requestAsync()
            if (response.data != null) {
                response.data!!.responseOk()
            }
        } catch (e: HttpException) {
            val error: ApiErrorModel = e.extractApiError()
            error.onApiErrorStatus()
        } catch (e: java.lang.Exception) {
            e.onException()
            e.printStackTrace()
            Log.e("ERROR1", "java.lang.Exception$e")
        }
    }
}