package com.pedrosiccha.ui.components.snackbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.pedrosiccha.domain.model.UiMessage

data class SnackbarConfig(
    val backgroundColor: Color,
    val contentColor: Color,
    val icon: ImageVector? = null,
    val duration: SnackbarDuration = SnackbarDuration.Short
)

object SnackbarMapper {
    fun from(message: UiMessage): SnackbarConfig = when (message) {
        is UiMessage.Error -> SnackbarConfig(
            backgroundColor = Color(0xFFE53935),
            contentColor = Color.White,
            icon = Icons.Default.Error,
            duration = SnackbarDuration.Long
        )
        is UiMessage.Success -> SnackbarConfig(
            backgroundColor = Color(0xFF43A047),
            contentColor = Color.White,
            icon = Icons.Default.Check,
            duration = SnackbarDuration.Short
        )
        is UiMessage.Warning -> SnackbarConfig(
            backgroundColor = Color(0xFFFFA000),
            contentColor = Color.Black,
            icon = Icons.Default.Warning,
            duration = SnackbarDuration.Long
        )
        is UiMessage.Info -> SnackbarConfig(
            backgroundColor = Color(0xFF1976D2),
            contentColor = Color.White,
            icon = Icons.Default.Info,
            duration = SnackbarDuration.Short
        )
    }
}