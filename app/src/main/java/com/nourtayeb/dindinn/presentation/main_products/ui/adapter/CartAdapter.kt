package com.nourtayeb.dindinn.presentation.main_products.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nourtayeb.dindinn.R
import com.nourtayeb.dindinn.common.base.BaseRecyclerViewAdapter
import com.nourtayeb.dindinn.common.loadImageFromUrl
import com.nourtayeb.dindinn.domain.entity.Product
import kotlinx.android.synthetic.main.list_item_cart.view.*

class CartAdapter : BaseRecyclerViewAdapter<Product, CartAdapter.CartItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_cart, parent, false)
        return CartItemViewHolder(itemView = itemView)
    }
    override fun addData(list: List<Product>?){
        if(list != this.list){
            super.addData(list)
        }
    }


    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getTotal() :Int {
        var acc = 0
        list.forEach { acc+= it.price }
        return acc
    }

    class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Product) {
            itemView.image?.loadImageFromUrl(item.image)
            itemView.productname.text = item.name
            itemView.price.text = "${item.price} usd"
        }
    }

}
