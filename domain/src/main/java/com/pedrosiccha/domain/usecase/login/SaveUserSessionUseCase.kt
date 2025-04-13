package com.pedrosiccha.domain.usecase.login
//Guarda sesión localmente

import com.pedrosiccha.domain.model.UserSession
import com.pedrosiccha.domain.repository.AuthRepository

class SaveUserSessionUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(session: UserSession) {
        repository.saveSession(session)
    }
}