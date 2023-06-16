package com.example.mviexample.data.error

class ApiErrorModel(
    private val status: Int? = null,
    private val message: String?,
    private val description: String? = null,
) : InterpretedError {
    override val userFriendlyInterpretation: String
        get() = description
            ?: when (message) {
                ErrorApiType.UNAUTHORIZED.message -> {
                    "Не авторизован"
                }
                else -> {
                    "$status $message"
                }
            }

    companion object {
        const val UNKNOWN_ERROR = "Неизвестная ошибка" //TODO: локализовать
    }
}