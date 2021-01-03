package com.nourtayeb.dindinn.data.repository

import com.nourtayeb.dindinn.data.networking.ApiService
import com.nourtayeb.dindinn.domain.entity.Promotion
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class PromotionRepository @Inject constructor(val apiService: ApiService) {
    fun getPromotions(): Single<List<Promotion>> {
        return apiService.getPromotions()
    }
}