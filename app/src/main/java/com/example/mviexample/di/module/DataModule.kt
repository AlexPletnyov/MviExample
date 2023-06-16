package com.example.mviexample.di.module

import com.example.mviexample.data.repository.ActionRepositoryImpl
import com.example.mviexample.domain.repository.ActionRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindActionRepository(impl: ActionRepositoryImpl): ActionRepository
}