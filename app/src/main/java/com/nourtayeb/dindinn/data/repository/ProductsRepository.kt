package com.nourtayeb.dindinn.data.repository

import com.nourtayeb.dindinn.data.networking.ApiService
import javax.inject.Inject

class ProductsRepository @Inject constructor(val apiService: ApiService)  {
    fun getProducts() = apiService.getProducts()
    fun getCategories() = apiService.getCategories()
    fun getCart() = apiService.getCart()
    fun addToCart( productId:Long) = apiService.addToCart(productId)
}