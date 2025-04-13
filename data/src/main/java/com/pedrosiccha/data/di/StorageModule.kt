package com.pedrosiccha.data.di

import android.content.Context
import com.pedrosiccha.data.local.session.SessionStorage
import com.pedrosiccha.data.local.session.SessionStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    fun provideSessionStorage(
        @ApplicationContext context: Context
    ): SessionStorage {
        return SessionStorageImpl(context)
    }

}