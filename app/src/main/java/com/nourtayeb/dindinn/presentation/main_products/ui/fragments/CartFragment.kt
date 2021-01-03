package com.nourtayeb.dindinn.presentation.main_products.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.airbnb.mvrx.*
import com.nourtayeb.dindinn.R
import com.nourtayeb.dindinn.presentation.main_products.ui.ProductsViewModel
import com.nourtayeb.dindinn.presentation.main_products.ui.adapter.CartAdapter
import com.nourtayeb.dindinn.presentation.main_products.ui.adapter.ProductsAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products.recyclerView

class CartFragment : BaseMvRxFragment() {

    lateinit var navController: NavController
    private val viewModel: ProductsViewModel by activityViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            when (state.cart) {
                is Loading -> {
                }
                is Success -> {
                    val cart =state.cart.invoke()
                    cartAdapter.addData(state.products.invoke()?.filter { cart.map { it.productId }.contains(it.id) })
                    total.text = "${cartAdapter.getTotal()} usd"
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

    lateinit var cartAdapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        back.setOnClickListener{
            goBack()
        }
    }

    fun goBack(){
        navController.popBackStack()
    }
    fun setUpRecyclerView() {
        cartAdapter = CartAdapter()
        with(recyclerView) {
            adapter = cartAdapter
            layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
                1,
                androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
            )
        }
    }


}