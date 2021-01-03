package com.nourtayeb.movies_mvi.common.di

import com.nourtayeb.dindinn.data.networking.ApiService
import com.nourtayeb.dindinn.data.repository.ProductsRepository
import com.nourtayeb.dindinn.data.repository.PromotionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataRepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepo(apiService: ApiService): ProductsRepository {
        return ProductsRepository(apiService)
    }
    @Provides
    @Singleton
    fun providePromotionsRepo(apiService: ApiService): PromotionRepository {
        return PromotionRepository(apiService)
    }
}