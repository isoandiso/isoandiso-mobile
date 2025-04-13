package com.pedrosiccha.domain.exception
//Excepciones esperadas del dominio (e.g., invalid credentials)

sealed class AuthException(message: String) : Exception(message) {
    object InvalidCredentials : AuthException("Credenciales incorrectas")
    object ServerError : AuthException("Error del servidor")
    object UnknownError : AuthException("Error desconocido")
}