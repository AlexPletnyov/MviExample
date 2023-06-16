package com.example.mviexample.data.repository

import com.example.mviexample.data.modlewares.GetTopCoinsMiddleware
import com.example.mviexample.domain.model.CoinInfo
import com.example.mviexample.domain.mvi.Action
import com.example.mviexample.domain.mvi.Result
import com.example.mviexample.domain.mvi.action.CoinAction
import com.example.mviexample.domain.mvi.result.GetTopCoinResult
import com.example.mviexample.domain.repository.ActionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActionRepositoryImpl @Inject constructor(
    private val getTopCoinsMiddleware: GetTopCoinsMiddleware
) : ActionRepository {
    override fun getActionResult(action: Action): Flow<Result> {
        return flow {
            when (action) {
                is CoinAction.GetTopCoinAction -> {
                    emit(GetTopCoinResult.Loading)
                    emit(getTopCoinsMiddleware.result(action) as Result)
                }
            }
        }
    }
}