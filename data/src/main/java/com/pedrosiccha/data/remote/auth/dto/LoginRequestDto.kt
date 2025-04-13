package com.pedrosiccha.data.remote.auth.dto
//Body del login

data class LoginRequestDto(
    val email: String,
    val password: String
)