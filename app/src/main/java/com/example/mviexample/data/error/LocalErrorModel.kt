package com.example.mviexample.data.error

data class LocalErrorModel(val localError: LocalError) :
    InterpretedError {
    override val userFriendlyInterpretation: String
        get() = when (localError) {
            LocalError.IO -> "Нет подключения к сети"
            LocalError.UNKNOWN_ERROR -> "Неизвестная ошибка"
        }
}