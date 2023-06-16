package com.example.mviexample.data.repository

import android.content.SharedPreferences
import com.example.mviexample.domain.repository.PreferencesRepository
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
) : PreferencesRepository {

}