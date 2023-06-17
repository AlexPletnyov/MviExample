package com.example.mviexample.di.module

import androidx.lifecycle.ViewModel
import com.example.mviexample.di.ViewModelKey
import com.example.mviexample.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}