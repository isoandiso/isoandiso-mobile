package com.pedrosiccha.feature.login.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pedrosiccha.domain.model.UiMessage
import com.pedrosiccha.feature.login.presentation.action.LoginAction
import com.pedrosiccha.feature.login.presentation.viewmodel.LoginViewModel
import com.pedrosiccha.ui.components.snackbar.CustomSnackbarHost
import com.pedrosiccha.ui.components.snackbar.ErrorSnackbarHost
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onRegister: () -> Unit,
    onForgotPassword: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.action.collectLatest { action ->
            when (action) {
                is LoginAction.LoginSuccess -> onLoginSuccess()
                is LoginAction.ShowSnackbar -> snackbarHostState.showSnackbar(
                    message = when (action.message) {
                        is UiMessage.Error -> "Error:${action.message.message}"
                        is UiMessage.Success -> "Success:${action.message.message}"
                        else -> "Error:${action.message.message}"
                    },
                    withDismissAction = true
                )
                LoginAction.NavigateToRegister -> onRegister()
                LoginAction.NavigateToForgotPassword -> onForgotPassword()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LoginContent(
            state = state,
            onEvent = viewModel::onEvent
        )

//        ErrorSnackbarHost(hostState = snackbarHostState)
        CustomSnackbarHost(hostState = snackbarHostState)
    }
}