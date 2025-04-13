package com.pedrosiccha.feature.login.presentation.viewmodel

import com.pedrosiccha.domain.model.UserSession
import com.pedrosiccha.domain.repository.AuthRepository
import com.pedrosiccha.domain.usecase.login.LoginUseCase
import com.pedrosiccha.feature.login.presentation.action.LoginAction
import com.pedrosiccha.feature.login.presentation.event.LoginEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var fakeLoginUseCase: LoginUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher()) // Usamos dispatcher de test para control
        fakeLoginUseCase = LoginUseCase(
            object : AuthRepository {
                override suspend fun login(email: String, password: String): UserSession {
                    return UserSession(id = "1", name = "Pedro", email = email, token = "token_abc")
                }

                override suspend fun saveSession(session: UserSession) {}
                override suspend fun getSession(): UserSession? = null
                override suspend fun logout() {}
            }
        )
        viewModel = LoginViewModel(fakeLoginUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loginExitoso_emiteLoginSuccess() = runTest {
        // Simulamos entrada del usuario
        viewModel.onEvent(LoginEvent.OnEmailChanged("pedro@gmail.com"))
        viewModel.onEvent(LoginEvent.OnPasswordChanged("123456"))
        viewModel.onEvent(LoginEvent.OnLoginClick)

        // Esperamos la acción emitida
        val action = viewModel.action.first()

        assertEquals(LoginAction.LoginSuccess, action)
    }

    @Test
    fun clickEnRegistrarse_emiteNavigateToRegister() = runTest {
        viewModel.onEvent(LoginEvent.OnRegisterClick)

        val action = viewModel.action.first()

        assertEquals(LoginAction.NavigateToRegister, action)
    }

    @Test
    fun onEmailChanged_actualizaStateCorrectamente() = runTest {
        val nuevoEmail = "nuevo@correo.com"
        // Cuando se envía el evento de cambio de email
        viewModel.onEvent(LoginEvent.OnEmailChanged(nuevoEmail))
        // Validamos que el estado del ViewModel se actualiza
        assertEquals(nuevoEmail, viewModel.state.value.email)
    }

    @Test
    fun onPasswordChanged_actualizaStateCorrectamente() = runTest {
        val nuevaPassword = "nuevaContrasena"
        // Simulamos el cambio en el valor de la contraseña
        viewModel.onEvent(LoginEvent.OnPasswordChanged(nuevaPassword))
        // Verificamos que el state refleja el nuevo valor
        assertEquals(nuevaPassword, viewModel.state.value.password)
    }

    @Test
    fun onForgotPasswordClick_emiteNavigateToForgotPassword() = runTest {
        // Al enviar el evento para "olvidé mi contraseña"
        viewModel.onEvent(LoginEvent.OnForgotPasswordClick)
        // Capturamos la acción emitida desde el SharedFlow del ViewModel
        val action = viewModel.action.first()
        // Validamos que la acción es la esperada
        assertEquals(LoginAction.NavigateToForgotPassword, action)
    }

}