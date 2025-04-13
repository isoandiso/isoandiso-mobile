package com.pedrosiccha.isoandiso_mobile.di
//DI general: red, base de datos, etc.

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMessage(): String = "Hola desde Hilt 🛠️"
}