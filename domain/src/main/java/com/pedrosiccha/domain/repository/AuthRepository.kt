package com.pedrosiccha.domain.repository

import com.pedrosiccha.domain.model.UserSession

//Contrato que define login y manejo de sesión

interface AuthRepository {
    suspend fun login(email: String, password: String): UserSession
    suspend fun saveSession(session: UserSession)
    suspend fun getSession(): UserSession?
    suspend fun logout()
}