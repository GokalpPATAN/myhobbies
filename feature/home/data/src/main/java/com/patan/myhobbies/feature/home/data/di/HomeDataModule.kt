package com.patan.myhobbies.feature.home.data.di

import com.patan.myhobbies.feature.home.data.remote.MyHobbiesApi
import com.patan.myhobbies.feature.home.data.repository.HomeRepositoryImpl
import com.patan.myhobbies.feature.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        impl: HomeRepositoryImpl
    ): HomeRepository

    companion object {

        @Provides
        @Singleton
        fun provideMyHobbiesApi(
            retrofit: Retrofit
        ): MyHobbiesApi = retrofit.create(MyHobbiesApi::class.java)
    }
}