package com.nourtayeb.dindinn.domain.usecase

import com.nourtayeb.dindinn.data.repository.PromotionRepository
import com.nourtayeb.dindinn.domain.entity.Product
import com.nourtayeb.dindinn.domain.entity.Promotion
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetPromotionsUseCase @Inject constructor(val promotionRepository: PromotionRepository) {
    fun buildUseCase(scheduler: Scheduler): Single<List<Promotion>> {
        return promotionRepository.getPromotions()
            .subscribeOn(scheduler)

    }
}