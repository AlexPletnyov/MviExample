package com.example.mviexample.di.module

import com.example.mviexample.data.error.ErrorHandler
import com.example.mviexample.data.error.GeneralErrorHandlerImpl
import dagger.Binds
import dagger.Module

@Module
interface HandleErrorModule {

    @Binds
    fun bindErrorHandler(impl: GeneralErrorHandlerImpl): ErrorHandler
}