package com.pedrosiccha.core.exception
//Traduce errores comunes (401, timeout, etc.)

sealed class GlobalException : Exception() {
    object NetworkUnavailable : GlobalException()
    object Unauthorized : GlobalException()
    data class HttpError(val code: Int, override val message: String?) : GlobalException()
    data class Unknown(override val cause: Throwable?) : GlobalException()
}