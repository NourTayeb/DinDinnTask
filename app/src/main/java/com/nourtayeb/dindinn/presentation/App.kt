package com.nourtayeb.dindinn.presentation

import android.app.Application
import com.nourtayeb.dindinn.data.repository.ProductsRepository
import com.nourtayeb.dindinn.data.repository.PromotionRepository
import com.nourtayeb.dindinn.domain.usecase.*
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.Scheduler
import javax.inject.Inject

@HiltAndroidApp
class App :Application(){
    @Inject
    lateinit var getPromotionsUseCase: GetPromotionsUseCase
    @Inject
    lateinit var getProductsUseCase: GetProductsUseCase
    @Inject
    lateinit var addToCartUseCase: AddToCartUseCase
    @Inject
    lateinit var getCartUseCase: GetCartUseCase
    @Inject
    lateinit var getCategoriesUseCase: GetCategoriesUseCase
    @Inject
    lateinit var scheduler: Scheduler
}