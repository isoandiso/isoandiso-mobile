package com.pedrosiccha.data.local.session
//DataStore / SharedPref

import com.pedrosiccha.domain.model.UserSession

interface SessionStorage {
    suspend fun saveSession(session: UserSession)
    suspend fun getSession(): UserSession?
    suspend fun clearSession()
}