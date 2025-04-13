package com.pedrosiccha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_session")
data class UserSessionEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val token: String
)