package com.example.mviexample.domain.repository

import com.example.mviexample.domain.mvi.Action
import com.example.mviexample.domain.mvi.Result
import kotlinx.coroutines.flow.Flow

interface ActionRepository {
    fun getActionResult(action: Action): Flow<Result>
}