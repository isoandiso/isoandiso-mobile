package com.pedrosiccha.data.mapper
//Mapeos DTO ↔ Dominio

import com.pedrosiccha.data.local.entity.UserSessionEntity
import com.pedrosiccha.data.remote.auth.dto.LoginResponseDto
import com.pedrosiccha.domain.model.UserSession

object AuthMapper {

    fun toEntity(dto: LoginResponseDto, token: String): UserSessionEntity {
        return UserSessionEntity(
            id = dto.id ?: "",
            name = dto.name,
            email = dto.email,
            token = token
        )
    }

    fun toDomain(entity: UserSessionEntity): UserSession {
        return UserSession(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            token = entity.token
        )
    }
}