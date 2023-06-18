package com.example.mviexample.domain.usecase

import com.example.mviexample.domain.repository.ApiRepository
import javax.inject.Inject

class GetTopCoinsUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke() = resultOf { apiRepository.getCoinInfoList() }
}