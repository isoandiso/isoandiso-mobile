package com.pedrosiccha.ui.components.snackbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pedrosiccha.ui.theme.ErrorColor

@Composable
fun ErrorSnackbarHost(
    hostState: SnackbarHostState
) {
    SnackbarHost(
        hostState = hostState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        snackbar = { data ->
            Snackbar(
                containerColor = ErrorColor, // rojo error
                contentColor = Color.White
            ) {
                Text(text = data.visuals.message)
            }
        }
    )
}