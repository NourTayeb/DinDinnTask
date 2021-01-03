package com.nourtayeb.dindinn.presentation.main_products.ui.adapter


import android.os.Bundle
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nourtayeb.dindinn.domain.entity.Category
import com.nourtayeb.dindinn.presentation.main_products.ui.fragments.ProductsFragment


class ProductsViewPager2Adapter(fragmentActivity: FragmentActivity, val categories: List<Category>) :
    FragmentStateAdapter(fragmentActivity) {
    var fragments : List<ProductsFragment>? =null

    override fun createFragment(position: Int) :ProductsFragment{
        return ProductsFragment.newInstance(categories[position].id)
    }

    override fun getItemCount() = fragments?.let { 0 } ?: categories.size

}