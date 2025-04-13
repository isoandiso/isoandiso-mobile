package com.pedrosiccha.data.di

import android.content.Context
import androidx.room.Room
import com.pedrosiccha.data.local.dao.UserSessionDao
import com.pedrosiccha.data.local.database.AppDatabase
import com.pedrosiccha.data.local.session.SessionStorage
import com.pedrosiccha.data.local.session.SessionStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "isoandiso_db"
        ).build()
    }

    @Provides
    fun provideUserSessionDao(
        database: AppDatabase
    ): UserSessionDao {
        return database.userSessionDao()
    }

//    @Provides
//    @Singleton
//    fun provideSessionStorage(
//        context: Context
//    ): SessionStorage {
//        return SessionStorageImpl(context)
//    }
}