package com.pedrosiccha.feature.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrosiccha.domain.model.AuthResult
import com.pedrosiccha.domain.model.UiMessage
import com.pedrosiccha.domain.model.message
import com.pedrosiccha.domain.usecase.login.LoginUseCase
import com.pedrosiccha.feature.login.presentation.action.LoginAction
import com.pedrosiccha.feature.login.presentation.event.LoginEvent
import com.pedrosiccha.feature.login.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _action = MutableSharedFlow<LoginAction>()
    val action: SharedFlow<LoginAction> = _action.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> _state.update { it.copy(email = event.value) }
            is LoginEvent.OnPasswordChanged -> _state.update { it.copy(password = event.value) }
            is LoginEvent.OnLoginClick -> login()
            is LoginEvent.OnRegisterClick -> emitAction(LoginAction.NavigateToRegister)
            is LoginEvent.OnForgotPasswordClick -> emitAction(LoginAction.NavigateToForgotPassword)
        }
    }

    private fun login() {

        val currentState = _state.value
        val emailError = if (currentState.email.isBlank()) "El correo es obligatorio" else null
        val passwordError = if (currentState.password.isBlank()) "La contraseña es obligatoria" else null

        // Si hay errores, actualizar estado y salir
        if (emailError != null || passwordError != null) {
            _state.update {
                it.copy(
                    emailError = emailError,
                    passwordError = passwordError,
                    generalError = null
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, emailError = null, passwordError = null, generalError = null) }
            val result = loginUseCase(_state.value.email, _state.value.password)
            _state.update { it.copy(isLoading = false) }

            when (result) {
                is AuthResult.Success -> {
                    emitAction(LoginAction.ShowSnackbar(UiMessage.Success("Bienvenido ${result.session.name}")))
//                    emitAction(LoginAction.LoginSuccess)
                }
                is AuthResult.Failure -> emitAction(
                    LoginAction.ShowSnackbar(
                        UiMessage.Error(result.error.message())
                    )
                )
            }
        }
    }

    private fun emitAction(action: LoginAction) {
        viewModelScope.launch { _action.emit(action) }
    }
}