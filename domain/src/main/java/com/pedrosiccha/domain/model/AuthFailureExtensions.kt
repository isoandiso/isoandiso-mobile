package com.pedrosiccha.domain.model

fun AuthFailure.message(): String {
    return when (this) {
        AuthFailure.InvalidCredentials -> "Credenciales inválidas. Verifica tus datos."
        AuthFailure.ServerError -> "Error del servidor. Intenta más tarde."
        AuthFailure.NetworkError -> "Sin conexión a internet."
        is AuthFailure.Unknown -> this.message ?: "Error desconocido"
    }
}