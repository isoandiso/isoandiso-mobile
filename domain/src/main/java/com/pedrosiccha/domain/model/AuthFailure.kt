package com.pedrosiccha.domain.model

sealed class AuthFailure {
    object InvalidCredentials : AuthFailure()
    object ServerError : AuthFailure()
    object NetworkError : AuthFailure()
    data class Unknown(val message: String?) : AuthFailure()
}