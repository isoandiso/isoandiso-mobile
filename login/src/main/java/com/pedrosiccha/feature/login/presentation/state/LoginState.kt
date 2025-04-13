package com.pedrosiccha.feature.login.presentation.state

/**
 * Representa el estado actual del formulario de login.
 */
data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val generalError: String? = null
)
