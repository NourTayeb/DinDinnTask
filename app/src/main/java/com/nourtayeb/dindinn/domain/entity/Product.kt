package com.nourtayeb.dindinn.domain.entity

data class Product(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val image: String,
    val ingredients: String,
    val price: Int,
    val weight: String,
    val size: String,
    val filter: String,
    val addedToCart:Boolean = false
)