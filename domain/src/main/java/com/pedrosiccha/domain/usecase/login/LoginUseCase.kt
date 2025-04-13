package com.pedrosiccha.domain.usecase.login
//Caso de uso que ejecuta el login

import com.pedrosiccha.domain.exception.AuthException
import com.pedrosiccha.domain.model.AuthFailure
import com.pedrosiccha.domain.model.AuthResult
import com.pedrosiccha.domain.repository.AuthRepository
import java.util.concurrent.CancellationException

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        return try {
            val session = repository.login(email, password)
            AuthResult.Success(session)
        } catch (e: CancellationException) {
            throw e
        } catch (e: AuthException) {
            val failure = when (e) {
                AuthException.InvalidCredentials -> AuthFailure.InvalidCredentials
                AuthException.ServerError -> AuthFailure.ServerError
                AuthException.UnknownError -> AuthFailure.Unknown(e.message)
            }
            AuthResult.Failure(failure)
        } catch (e: Exception) {
            AuthResult.Failure(AuthFailure.Unknown(e.message))
        }
    }
}