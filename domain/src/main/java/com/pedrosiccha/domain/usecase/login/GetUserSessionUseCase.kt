package com.pedrosiccha.domain.usecase.login

import com.pedrosiccha.domain.model.UserSession
import com.pedrosiccha.domain.repository.AuthRepository

class GetUserSessionUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): UserSession? = repository.getSession()
}