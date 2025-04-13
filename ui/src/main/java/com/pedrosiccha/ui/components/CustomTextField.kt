package com.pedrosiccha.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.pedrosiccha.ui.theme.ErrorColor
import com.pedrosiccha.ui.theme.TextColor

@Composable
fun CustomTextField(
    label: String,

    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: Int? = null, // drawable resource ID
    hasError: Boolean = false,
    errorMessage: String = ""
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = TextColor,
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text(text = placeholder) },
            isError = hasError,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (isPassword && !passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            leadingIcon = {
                if (leadingIcon != null) {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = null
                    )
                }
            },
            trailingIcon = {
                if (isPassword) {
                    val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Toggle password visibility")
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (hasError) ErrorColor else MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = if (hasError) ErrorColor else MaterialTheme.colorScheme.outline,
                cursorColor = if (hasError) ErrorColor else MaterialTheme.colorScheme.primary,
                focusedTextColor = TextColor,
                unfocusedTextColor = TextColor,
                errorTextColor = TextColor,
                errorBorderColor = ErrorColor,
                errorPlaceholderColor = ErrorColor,
                errorCursorColor = ErrorColor,
                errorSupportingTextColor = ErrorColor
            )
        )

        if (hasError && errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = ErrorColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    CustomTextField(
        label = "Correo",
        value = text,
        onValueChange = { text = it },
        placeholder = "Escribe tu correo",
        keyboardType = KeyboardType.Email,
        isPassword = false,
        enabled = true,
        hasError = false
    )
}