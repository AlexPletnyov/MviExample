package com.example.mviexample.di.component

import android.app.Application
import com.example.mviexample.di.module.ContextModule
import com.example.mviexample.di.module.DataModule
import com.example.mviexample.di.module.LoggerModule
import com.example.mviexample.di.module.NetworkModule
import com.example.mviexample.di.module.PreferencesModule
import com.example.mviexample.di.module.ViewModelModule
import com.example.mviexample.di.scopes.ApplicationScope
import com.example.mviexample.domain.GetCoinsFragment
import com.example.mviexample.presentation.MainViewModel
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ContextModule::class,
        DataModule::class,
        NetworkModule::class,
        PreferencesModule::class,
        ViewModelModule::class,
        LoggerModule::class
    ]
)
interface MviExampleComponent {

//    fun inject(wrapper: GetCoinsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getApplication(application: Application): Builder

        fun build(): MviExampleComponent
    }
}