package com.example.mviexample.di.module

import androidx.lifecycle.ViewModel
import com.example.mviexample.di.ViewModelKey
import com.example.mviexample.domain.repository.ActionRepository
import com.example.mviexample.domain.repository.PreferencesRepository
import com.example.mviexample.domain.usecase.GetActionResultUseCase
import com.example.mviexample.presentation.AppLogger
import com.example.mviexample.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}