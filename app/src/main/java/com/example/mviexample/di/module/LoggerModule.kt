package com.example.mviexample.di.module

import com.example.mviexample.presentation.AppLogger
import dagger.Module
import dagger.Provides

@Module
class LoggerModule {
    @Provides
    fun provideLogger() = AppLogger()
}