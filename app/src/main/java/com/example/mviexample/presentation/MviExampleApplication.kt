package com.example.mviexample.presentation

import android.app.Application
import com.example.mviexample.di.component.DaggerMviExampleComponent

class MviExampleApplication : Application() {

    val component by lazy {
        DaggerMviExampleComponent
            .builder()
            .getApplication(this)
            .build()
    }
}