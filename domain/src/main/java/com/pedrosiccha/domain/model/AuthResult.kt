package com.pedrosiccha.domain.model
//Resultado del login (éxito o error)

sealed class AuthResult {
    data class Success(val session: UserSession) : AuthResult()
    data class Failure(val error: AuthFailure) : AuthResult()
}