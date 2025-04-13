package com.pedrosiccha.ui.components.snackbar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.pedrosiccha.domain.model.UiMessage

@Composable
fun CustomSnackbarHost(hostState: SnackbarHostState) {
    SnackbarHost(hostState) { data ->
        // Usa la clave para obtener el tipo real
        val rawMessage = data.visuals.message
        val uiMessage = if (rawMessage.startsWith("Success:")) {
            UiMessage.Success(rawMessage.removePrefix("Success:"))
        } else {
            UiMessage.Error(rawMessage.removePrefix("Error:"))
        }

        val config = SnackbarMapper.from(uiMessage)

        Snackbar(
            containerColor = config.backgroundColor,
            contentColor = config.contentColor,
        ) {
            Text(text = uiMessage.message)
        }
    }
}