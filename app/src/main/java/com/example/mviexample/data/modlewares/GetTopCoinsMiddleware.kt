package com.example.mviexample.data.modlewares

import com.example.mviexample.data.error.MapErrorUtil.extractLocalError
import com.example.mviexample.data.mapper.DataMapper
import com.example.mviexample.domain.mvi.Middleware
import com.example.mviexample.domain.mvi.action.CoinAction
import com.example.mviexample.domain.mvi.result.GetTopCoinResult
import com.example.mviexample.domain.repository.ApiRepository
import javax.inject.Inject

class GetTopCoinsMiddleware @Inject constructor(
    apiRepository: ApiRepository,
    dataMapper: DataMapper
) : Middleware<CoinAction, GetTopCoinResult>(
    apiRepository = apiRepository,
    mapper = dataMapper
) {
    override suspend fun result(action: CoinAction): GetTopCoinResult? {
        var result: GetTopCoinResult? = null
        with(action) {
            if (this is CoinAction.GetTopCoinAction) {
                doRequest(
                    requestAsync = {
                        apiRepository.getCoinInfoList()
                    },
                    responseOk = {
                        result = GetTopCoinResult.Success(
                            getTopCoinsResponse = mapper?.mapCoinNamesListDtoToGetTopCoinsResponse(
                                this
                            )
                        )
                    },
                    onApiErrorStatus = {
                        result = GetTopCoinResult.Failure(interpretedError = this)
                    },
                    onException = {
                        result = GetTopCoinResult.Failure(interpretedError = extractLocalError())
                    }
                )
            }
        }
        return result
    }
}