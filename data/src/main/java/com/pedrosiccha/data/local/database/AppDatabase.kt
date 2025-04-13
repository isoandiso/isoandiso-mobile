package com.pedrosiccha.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedrosiccha.data.local.dao.UserSessionDao
import com.pedrosiccha.data.local.entity.UserSessionEntity

@Database(
    entities = [UserSessionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userSessionDao(): UserSessionDao
}