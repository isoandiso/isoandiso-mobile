package com.pedrosiccha.feature.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.pedrosiccha.feature.login.presentation.event.LoginEvent
import com.pedrosiccha.feature.login.presentation.state.LoginState
import com.pedrosiccha.ui.R
import com.pedrosiccha.ui.components.*
import com.pedrosiccha.ui.theme.PrimaryColor

@Composable
fun LoginContent(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔵 Encabezado con fondo azul
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryColor)
                .padding(top = 48.dp, bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = "Rápido, eficiente y productivo",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp
                )
            }
        }

        // ⚪️ Contenido del formulario
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            Text("Iniciar sesión", style = MaterialTheme.typography.headlineSmall)
            Text("¡Simplifica, optimiza, crece!", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(24.dp))

            CustomTextField(
                label = "Correo",
                value = state.email,
                onValueChange = { onEvent(LoginEvent.OnEmailChanged(it)) },
                placeholder = "Email",
                keyboardType = KeyboardType.Email,
                hasError = state.emailError != null,
                errorMessage = state.emailError.orEmpty(),
            )

            Spacer(Modifier.height(16.dp))

            CustomTextField(
                label = "Contraseña",
                value = state.password,
                onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                placeholder = "Contraseña",
                isPassword = true,
                keyboardType = KeyboardType.Password,
                hasError = state.passwordError != null,
                errorMessage = state.passwordError.orEmpty()
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                LinkButton(
                    text = "¿Olvidaste tu contraseña?",
                    onClick = { onEvent(LoginEvent.OnForgotPasswordClick) },
                    fontSize = 13
                )
            }

            Spacer(Modifier.height(24.dp))

            PrimaryButton(
                text = "Entrar",
                isLoading = state.isLoading,
                onClick = { onEvent(LoginEvent.OnLoginClick) }
            )

            Spacer(Modifier.height(16.dp))

            LinkButton(
                text = "¿Aún no eres miembro? Regístrate",
                onClick = { onEvent(LoginEvent.OnRegisterClick) },
                textAlign = TextAlign.Center,
                fontSize = 13
            )

            Spacer(Modifier.weight(1f))

            FooterLinks(
                links = listOf(
                    "Términos" to { /* TODO */ },
                    "Planes" to { /* TODO */ },
                    "Contáctanos" to { /* TODO */ }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}