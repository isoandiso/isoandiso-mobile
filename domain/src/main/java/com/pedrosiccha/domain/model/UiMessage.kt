package com.pedrosiccha.domain.model

sealed class UiMessage(open val message: String) {
    data class Error(override val message: String) : UiMessage(message)
    data class Success(override val message: String) : UiMessage(message)
    data class Warning(override val message: String) : UiMessage(message)
    data class Info(override val message: String) : UiMessage(message)
}