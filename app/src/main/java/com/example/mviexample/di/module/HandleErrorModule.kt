package com.example.mviexample.di.module

import com.example.mviexample.domain.repository.ErrorHandler
import com.example.mviexample.data.repository.GeneralErrorHandlerImpl
import dagger.Binds
import dagger.Module

@Module
interface HandleErrorModule {

    @Binds
    fun bindErrorHandler(impl: GeneralErrorHandlerImpl): ErrorHandler
}