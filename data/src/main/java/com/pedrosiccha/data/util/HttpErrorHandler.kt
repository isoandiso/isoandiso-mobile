package com.pedrosiccha.data.util

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

object HttpErrorHandler {
    data class ApiError(
        val message: String?,
        val error: String?
    )

    fun parse(exception: Throwable): String {
        return when (exception) {
            is HttpException -> {
                val errorBody = exception.response()?.errorBody()?.string()
                try {
                    val apiError = Gson().fromJson(errorBody, ApiError::class.java)
                    apiError.error ?: apiError.message ?: "Ocurrió un error inesperado."
                } catch (e: Exception) {
                    "Error desconocido del servidor."
                }
            }
            is IOException -> "No se pudo conectar con el servidor. Verifica tu conexión."
            else -> exception.message ?: "Error desconocido"
        }
    }
}