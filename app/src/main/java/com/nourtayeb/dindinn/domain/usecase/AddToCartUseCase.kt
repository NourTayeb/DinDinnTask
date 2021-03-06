package com.nourtayeb.dindinn.domain.usecase

import com.nourtayeb.dindinn.data.repository.ProductsRepository
import com.nourtayeb.dindinn.data.repository.PromotionRepository
import com.nourtayeb.dindinn.domain.entity.CartItem
import com.nourtayeb.dindinn.domain.entity.Product
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(val productsRepository: ProductsRepository) {
    fun buildUseCase(scheduler: Scheduler, productId: Long): Single<CartItem> {
        return productsRepository.addToCart(productId)
            .subscribeOn(scheduler)
    }
}