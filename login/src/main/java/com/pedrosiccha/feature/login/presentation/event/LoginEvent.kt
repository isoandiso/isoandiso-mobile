package com.pedrosiccha.feature.login.presentation.event

sealed class LoginEvent {
    data class OnEmailChanged(val value: String) : LoginEvent()
    data class OnPasswordChanged(val value: String) : LoginEvent()
    object OnLoginClick : LoginEvent()
    object OnRegisterClick : LoginEvent()
    object OnForgotPasswordClick : LoginEvent()
}