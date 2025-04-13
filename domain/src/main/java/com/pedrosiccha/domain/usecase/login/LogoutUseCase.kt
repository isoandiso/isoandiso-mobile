package com.pedrosiccha.domain.usecase.login

import com.pedrosiccha.domain.repository.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.logout()
}