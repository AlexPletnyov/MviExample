package com.example.mviexample.data.error

import retrofit2.HttpException
import java.io.IOException

object MapErrorUtil {

    fun HttpException.extractApiError(): ApiErrorModel {

        val code = response()?.raw()?.code.toString()
        val message = response()?.raw()?.message.toString()

        this.printStackTrace()
        return ApiErrorModel(
            status = code.toInt(),
            message = message,
            description = null
        )
    }

    fun Exception.extractLocalError(): LocalErrorModel {
        return LocalErrorModel(
            if (this is IOException) {
                this.printStackTrace()
                LocalError.IO
            } else {
                this.printStackTrace()
                LocalError.UNKNOWN_ERROR
            }
        )
    }
}