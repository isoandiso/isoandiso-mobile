package com.pedrosiccha.feature.login.navigation

sealed class LoginNavigation {
    object NavigateToDashboard : LoginNavigation()
    object NavigateToRegister : LoginNavigation()
    object NavigateToForgotPassword : LoginNavigation()
}