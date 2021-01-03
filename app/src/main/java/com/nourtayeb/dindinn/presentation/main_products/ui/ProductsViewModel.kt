package com.nourtayeb.dindinn.presentation.main_products.ui

import com.airbnb.mvrx.*
import com.nourtayeb.dindinn.domain.usecase.*
import com.nourtayeb.dindinn.presentation.App
import io.reactivex.Scheduler

class ProductsViewModel constructor(
    val scheduler: Scheduler,
    initialState: ProductsState,
    val getPromotionsUseCase: GetPromotionsUseCase,
    val getProductsUseCase: GetProductsUseCase,
    val getCartUseCase: GetCartUseCase,
    val addToCartUseCase: AddToCartUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase
) : BaseMvRxViewModel<ProductsState>(initialState, debugMode = true) {
    init {
        setState {
            copy(promotions = Loading(),categories = Loading(), products = Loading(),cart = Loading())
        }
        getPromotionsUseCase.buildUseCase(scheduler)
            .execute {
                copy(promotions = it)
            }
        getCategoriesUseCase.buildUseCase(scheduler)
            .execute {
                copy(categories = it)
            }
        getProductsUseCase.buildUseCase(scheduler)
            .execute {
                copy(products = it)
            }
        getCartUseCase.buildUseCase(scheduler)
            .execute {
                if (it.invoke()?.isNotEmpty() ?: false) {
                    copy(cart = it)
                }else{
                    copy()
                }
            }
    }

    fun addToCart(id: Long) {
        withState { state ->
            if (state.products is Success) {
                addToCartUseCase.buildUseCase(scheduler, id)
                    .execute {
                        if (it is Success) {
                            val cart = state.cart.invoke()
                            val cartItem = it.invoke()
                            when {
                                cart == null -> {
                                    copy(cart = Success(setOf(cartItem)))
                                }
                                cart.contains(cartItem) -> {
                                cart.first { item -> cartItem == item }.quantity =
                                    cartItem.quantity
                                    copy(cart = Success(cart))
                                }
                                !cart.contains(cartItem) -> {
                                    copy(cart = Success(
                                        cart.toMutableSet().apply {
                                            add(cartItem)
                                        }
                                    ))
                                }
                                else -> copy()
                            }
                        } else if (it is Fail) {
                            state
                        } else {
                            state
                        }
                    }
            }
        }
    }

    companion object : MvRxViewModelFactory<ProductsViewModel, ProductsState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: ProductsState
        ): ProductsViewModel? {
            return ProductsViewModel(
                viewModelContext.app<App>().scheduler,
                state,
                viewModelContext.app<App>().getPromotionsUseCase,
                viewModelContext.app<App>().getProductsUseCase,
                viewModelContext.app<App>().getCartUseCase,
                viewModelContext.app<App>().addToCartUseCase,
                viewModelContext.app<App>().getCategoriesUseCase
            )
        }
    }
}