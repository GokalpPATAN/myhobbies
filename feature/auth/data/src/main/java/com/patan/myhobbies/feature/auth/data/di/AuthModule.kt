package com.patan.myhobbies.feature.auth.data.di

import com.patan.myhobbies.feature.auth.data.google.GoogleAuthManager
import com.patan.myhobbies.feature.auth.data.repository.AuthRepositoryImpl
import com.patan.myhobbies.feature.auth.domain.manager.GoogleSignInHandler
import com.patan.myhobbies.feature.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindGoogleSignInHandler(impl: GoogleAuthManager): GoogleSignInHandler
}