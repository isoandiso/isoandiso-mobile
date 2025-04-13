package com.pedrosiccha.data.remote.auth.dto

import com.google.gson.annotations.SerializedName

//Respuesta de la API

data class LoginResponseDto(
    @SerializedName("_id")
    val id: String?,
    val name: String,
    val email: String
)