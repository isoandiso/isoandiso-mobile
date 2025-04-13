package com.pedrosiccha.data.repository
//Implementación concreta de AuthRepository

import com.pedrosiccha.data.local.dao.UserSessionDao
import com.pedrosiccha.data.local.session.SessionStorage
import com.pedrosiccha.data.mapper.AuthMapper
import com.pedrosiccha.data.remote.auth.AuthApi
import com.pedrosiccha.data.remote.auth.dto.LoginRequestDto
import com.pedrosiccha.data.remote.auth.dto.LoginResponseDto
import com.pedrosiccha.data.util.HttpErrorHandler
import com.pedrosiccha.domain.model.UserSession
import com.pedrosiccha.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val dao: UserSessionDao,
    private val sessionStorage: SessionStorage
) : AuthRepository {

    override suspend fun login(email: String, password: String): UserSession {
        try {
            val response = api.login(LoginRequestDto(email, password))

            if (response.isSuccessful) {
                val body = response.body() ?: throw Exception("Respuesta vacía del servidor")

                // ✅ 1. Extraer el token del header "Set-Cookie"
                val rawCookie = response.headers()["Set-Cookie"]
                val token = rawCookie
                    ?.split(";")
                    ?.firstOrNull { it.trim().startsWith("token=") }
                    ?.substringAfter("token=")
                    ?: throw Exception("Token no recibido en Set-Cookie")

                // ✅ 2. Guardar sesión en Room + SessionStorage
                val entity = AuthMapper.toEntity(body, token)

                dao.clearSession()
                dao.saveSession(entity)
                sessionStorage.saveSession(AuthMapper.toDomain(entity))

                return AuthMapper.toDomain(entity)
            } else {
                // Puedes parsear error si quieres más detalle
                throw Exception(HttpErrorHandler.parse(retrofit2.HttpException(response)))
            }

        } catch (e: IOException) {
            throw Exception("Error de red. Verifica tu conexión.")
        } catch (e: Exception) {
            throw Exception(e.message ?: "Error desconocido")
        }
    }

    override suspend fun saveSession(session: UserSession) {
        sessionStorage.saveSession(session)
    }

    override suspend fun getSession(): UserSession? {
        return sessionStorage.getSession()
    }

    override suspend fun logout() {
        dao.clearSession()
        sessionStorage.clearSession()
    }
}
