package com.patan.myhobbies.di

import android.content.Context
import com.patan.myhobbies.R
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
object GoogleModule {

    @Provides
    @Singleton
    @Named("webClientId")
    fun provideWebClientId(
        @ApplicationContext context: Context
    ): String = context.getString(R.string.default_web_client_id)
}