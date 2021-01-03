package com.nourtayeb.dindinn.common.di

import com.nourtayeb.dindinn.data.networking.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkingModule {


    @Provides
    @Singleton
    fun provideRestApiService(): ApiService {
        return ApiService()
    }
}