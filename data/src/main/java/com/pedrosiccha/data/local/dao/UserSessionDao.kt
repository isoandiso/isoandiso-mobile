package com.pedrosiccha.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedrosiccha.data.local.entity.UserSessionEntity

@Dao
interface UserSessionDao {
    @Query("SELECT * FROM user_session LIMIT 1")
    suspend fun getSession(): UserSessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(session: UserSessionEntity)

    @Query("DELETE FROM user_session")
    suspend fun clearSession()
}