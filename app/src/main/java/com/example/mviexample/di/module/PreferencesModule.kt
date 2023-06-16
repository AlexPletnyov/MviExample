package com.example.mviexample.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.mviexample.data.network.Config
import com.example.mviexample.data.repository.PreferencesRepositoryImpl
import com.example.mviexample.domain.repository.PreferencesRepository
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(
            Config.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    fun providePreferences(sharedPreferences: SharedPreferences): PreferencesRepository {
        return PreferencesRepositoryImpl(sharedPreferences)
    }
}