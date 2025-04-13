package com.pedrosiccha.data.remote.auth
//# Interface Retrofit

import com.pedrosiccha.data.remote.auth.dto.LoginRequestDto
import com.pedrosiccha.data.remote.auth.dto.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/user/login")
    suspend fun login(@Body request: LoginRequestDto): Response<LoginResponseDto>
}