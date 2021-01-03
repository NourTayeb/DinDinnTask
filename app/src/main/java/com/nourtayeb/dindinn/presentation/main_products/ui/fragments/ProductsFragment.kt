package com.nourtayeb.dindinn.presentation.main_products.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.nourtayeb.dindinn.R
import com.nourtayeb.dindinn.presentation.main_products.ui.ProductsViewModel
import com.nourtayeb.dindinn.presentation.main_products.ui.adapter.ProductsAdapter
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : BaseMvRxFragment() {


    private val viewModel: ProductsViewModel by activityViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            when (state.products) {
                is Loading -> {
                    showProductsLoading()
                }
                is Success -> {
                    hideProductsLoading()
                    val products = state.products.invoke().filter { it.categoryId == categoryId }
                    if(products.isEmpty()){
                        showNoProducts()
                    }else {
                        productsAdapter.addData(products)
                    }
                }
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        "Failed to load all promotions",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

     fun showProductsLoading() {
        productsLoading.visibility = View.VISIBLE
    }
    fun hideProductsLoading(){
        productsLoading.visibility = View.GONE
    }
    fun showNoProducts(){
        noProducts.visibility = View.VISIBLE
    }

    lateinit var productsAdapter: ProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        productsAdapter = ProductsAdapter()
        with(recyclerView) {
            adapter = productsAdapter
            layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
                1,
                androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
            )
        }
        with(productsAdapter) {
            onAddToCartClicked = { id -> addToCart(id) }
        }
    }

    fun addToCart(id: Long) {
        viewModel.addToCart(id)
    }
    var categoryId = 0L
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getLong("categoryId")?.let {
            categoryId = it
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(categoryId:Long) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putLong("categoryId", categoryId)
            }
        }
    }

}