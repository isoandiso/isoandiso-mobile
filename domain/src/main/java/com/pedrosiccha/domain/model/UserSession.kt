package com.pedrosiccha.domain.model
/**
 * Modelo de dominio que representa la sesión del usuario autenticado.
 * Esta clase no debe conocer detalles de la capa de datos ni de almacenamiento.
 */
data class UserSession(
    val id: String,      // ID del usuario (debe ser no-nulo)
    val name: String,    // Nombre del usuario
    val email: String,   // Email asociado
    val token: String    // Token JWT para autenticación
)