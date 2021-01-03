package com.nourtayeb.dindinn.domain.usecase

import com.nourtayeb.dindinn.data.repository.ProductsRepository
import com.nourtayeb.dindinn.data.repository.PromotionRepository
import com.nourtayeb.dindinn.domain.entity.Product
import com.nourtayeb.dindinn.domain.entity.Promotion
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(val productsRepository: ProductsRepository) {
    fun buildUseCase(scheduler: Scheduler): Single<List<Product>> {
        return productsRepository.getProducts()
            .subscribeOn(scheduler)

    }
}