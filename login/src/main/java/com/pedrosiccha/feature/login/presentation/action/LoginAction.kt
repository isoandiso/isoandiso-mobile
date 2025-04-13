package com.pedrosiccha.feature.login.presentation.action

import com.pedrosiccha.domain.model.UiMessage

//Acciones emitidas por la UI

sealed class LoginAction {
    object LoginSuccess : LoginAction()
    data class ShowSnackbar(val message: UiMessage) : LoginAction()
    object NavigateToRegister : LoginAction()
    object NavigateToForgotPassword : LoginAction()
}