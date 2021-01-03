
package com.nourtayeb.dindinn.presentation.main_products.ui

import  com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.nourtayeb.dindinn.domain.entity.CartItem
import com.nourtayeb.dindinn.domain.entity.Category
import com.nourtayeb.dindinn.domain.entity.Product
import com.nourtayeb.dindinn.domain.entity.Promotion

data class ProductsState(
    val promotions: Async<List<Promotion>> = Uninitialized,
    val products: Async<List<Product>> = Uninitialized,
    val categories: Async<List<Category>> = Uninitialized,
    val cart: Async<Set<CartItem>> = Uninitialized
) : MvRxState