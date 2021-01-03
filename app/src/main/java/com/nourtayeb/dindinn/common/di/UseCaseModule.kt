package com.nourtayeb.movies_mvi.common.di

import com.nourtayeb.dindinn.data.repository.ProductsRepository
import com.nourtayeb.dindinn.data.repository.PromotionRepository
import com.nourtayeb.dindinn.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    fun provideAddToCartUseCase(productsRepository: ProductsRepository): AddToCartUseCase {
        return AddToCartUseCase(productsRepository)
    }


    @Provides
    fun provideGetCartUseCase(productsRepository: ProductsRepository): GetCartUseCase {
        return GetCartUseCase(productsRepository)
    }

    @Provides
    fun provideGetProductsUseCase(productsRepository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCase(productsRepository)
    }

    @Provides
    fun provideGetPromotionsUseCase(promotionRepository: PromotionRepository): GetPromotionsUseCase {
        return GetPromotionsUseCase(promotionRepository)
    }

    @Provides
    fun provideGetCategoriesUseCase(productsRepository: ProductsRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(productsRepository)
    }


}