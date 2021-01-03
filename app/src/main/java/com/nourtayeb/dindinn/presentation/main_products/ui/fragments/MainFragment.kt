package com.nourtayeb.dindinn.presentation.main_products.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.mvrx.*
import com.google.android.material.tabs.TabLayoutMediator
import com.nourtayeb.dindinn.R
import com.nourtayeb.dindinn.domain.entity.Category
import com.nourtayeb.dindinn.domain.entity.Product
import com.nourtayeb.dindinn.presentation.main_products.ui.ProductsViewModel
import com.nourtayeb.dindinn.presentation.main_products.ui.adapter.ProductsViewPager2Adapter
import com.nourtayeb.dindinn.presentation.main_products.ui.adapter.SliderAdapterExample
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseMvRxFragment() {
    lateinit var navController: NavController

    var cartProductsCount = 0
    var categorySetup = false


    private val viewModel: ProductsViewModel by activityViewModel()
    lateinit var sliderAdapter: SliderAdapterExample
    override fun invalidate() {
        withState(viewModel) { state ->
            when (state.promotions) {
                is Loading -> {
                    showSliderLoading()
                }
                is Success -> {
                    hideSliderLoading()
                    sliderAdapter.addItems(state.promotions.invoke())
                }
                is Fail -> {
                    showToast(getString(R.string.failed_loading_promotions))
                }
            }
            when (state.cart) {
                is Loading -> {
                    hideCartCount()
                }
                is Success -> {
                    showCartCount()
                    setCartCount(state.cart.invoke().size)
                }
            }
            when (state.categories) {
                is Success -> {
                    hideProductsLoading()
                    setUpCategoriesWithTabs(state.categories.invoke())
                }
                is Loading -> {
                    showProductsLoading()
                }
                is Fail -> {
                    showToast(getString(R.string.failed_loading_categories))
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        categorySetup = false
    }
     fun hideProductsLoading() {
         productsLoader.visibility = View.GONE
     }
     fun showProductsLoading() {
         productsLoader.visibility = View.VISIBLE
     }

    fun setCartCount(size: Int) {
        cartProductsCount = size
         cartCount.text = "$size"
    }

    fun hideCartCount() {
        cartCount.visibility = View.GONE
    }
    fun showCartCount(){
        cartCount.visibility = View.VISIBLE
    }

    fun showSliderLoading() {
        sliderLoader.visibility = View.VISIBLE
    }
    fun hideSliderLoading(){
        sliderLoader.visibility = View.GONE
    }


    fun setupSlider() {
        sliderAdapter = SliderAdapterExample(requireContext())

    }
    fun attachSliderToAdapter(){
        imageSlider.setSliderAdapter(sliderAdapter);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        imageSlider.startAutoCycle();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSlider()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        fab.setOnClickListener {
            goToCart()
        }
        attachSliderToAdapter()
    }

    fun setUpCategoriesWithTabs(categories: List<Category>) {
        if (!categorySetup) {
            val pagerAdapter = ProductsViewPager2Adapter(requireActivity(), categories)
            view_pager.adapter = pagerAdapter
            TabLayoutMediator(
                tabs, view_pager
            ) { _, _ -> }.attach()

            for (i in 0 until tabs.getTabCount()) {
                tabs.getTabAt(i)?.setCustomView(R.layout.custom_tab)
                tabs.getTabAt(i)?.getCustomView()?.findViewById<TextView>(R.id.tab)?.text =
                    categories[i].name
            }
            view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                var currentPosition = 0
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tabs.getTabAt(currentPosition)?.getCustomView()
                        ?.findViewById<TextView>(R.id.tab)
                        ?.setTextColor(requireContext().getColor(R.color.gray))
                    tabs.getTabAt(position)?.getCustomView()?.findViewById<TextView>(R.id.tab)
                        ?.setTextColor(requireContext().getColor(R.color.productname))
                    currentPosition = position
                }

            })
            categorySetup = true
        }
    }

    fun goToCart() {
        if (cartProductsCount > 0)
            navController.navigate(R.id.action_mainFragment_to_cartFragment)
        else
            showToast(getString(R.string.cart_empty))
    }

    fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
    }

}