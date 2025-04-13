package com.pedrosiccha.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LinkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.End,
    fontSize: Int = 13, // 👈 Tamaño de texto configurable
) {
    Text(
        text = text,
        color = textColor,
        fontSize = fontSize.sp,
        textAlign = textAlign,
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 4.dp)
            .clickable { onClick() },
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(showBackground = true)
@Composable
fun LinkButtonPreview() {
    LinkButton(
        text = "¿Olvidaste tu contraseña?",
        onClick = {},
        textColor = Color(0xFF1B84FF),
        fontSize = 14,
        textAlign = TextAlign.End
    )
}