package com.pedrosiccha.data.di

import com.pedrosiccha.data.local.dao.UserSessionDao
import com.pedrosiccha.data.local.session.SessionStorage
import com.pedrosiccha.data.remote.auth.AuthApi
import com.pedrosiccha.data.repository.AuthRepositoryImpl
import com.pedrosiccha.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {

    @Provides
    fun provideAuthRepository(
        api: AuthApi,
        dao: UserSessionDao,
        sessionStorage: SessionStorage
    ): AuthRepository {
        return AuthRepositoryImpl(api, dao, sessionStorage)
    }
}