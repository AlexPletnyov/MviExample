package com.example.mviexample.domain.usecase

import com.example.mviexample.domain.mvi.Action
import com.example.mviexample.domain.repository.ActionRepository
import javax.inject.Inject

class GetActionResultUseCase @Inject constructor(
    private val actionRepository: ActionRepository
) {
    operator fun invoke(action: Action) = actionRepository.getActionResult(action)
}