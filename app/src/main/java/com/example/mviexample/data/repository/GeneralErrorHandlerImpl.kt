package com.example.mviexample.data.repository

import com.example.mviexample.domain.repository.ErrorHandler
import com.example.mviexample.presentation.common.AppLogger
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GeneralErrorHandlerImpl @Inject constructor(
    private val logger: AppLogger
) : ErrorHandler {

    init {
        logger.connect(javaClass)
    }

    override fun getErrorDescription(throwable: Throwable) : String {
        throwable.printStackTrace()
        logger.e(throwable.toString())
        return when (throwable) {
            is IOException -> "No internet connection!"
            is HttpException -> {
                val code = throwable.code()
                val message = throwable.message()
                "[$code] $message"
            }

            else -> "Unknown error!"
        }
    }
}
