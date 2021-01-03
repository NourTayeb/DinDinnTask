package com.nourtayeb.dindinn.presentation.main_products.ui.adapter

import android.animation.Animator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nourtayeb.dindinn.R
import com.nourtayeb.dindinn.common.base.BaseRecyclerViewAdapter
import com.nourtayeb.dindinn.common.loadImageFromUrl
import com.nourtayeb.dindinn.domain.entity.Product
import kotlinx.android.synthetic.main.list_item_product.view.*

class ProductsAdapter: BaseRecyclerViewAdapter<Product, ProductsAdapter.ProductViewHolder>() {
    var onAddToCartClicked: (Long) -> Unit = { _ -> }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_product, parent, false)
        return ProductViewHolder(itemView = itemView)
    }

    override fun addData(list: List<Product>?){
        if(list != this.list){
            super.addData(list)
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position),onAddToCartClicked)
    }


     class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Product, onAddToCartClicked: (Long) -> Unit = { _ ->}){
            itemView.image?.loadImageFromUrl(item.image)
            itemView.productname.text = item.name
            itemView.ingriedient.text = item.ingredients
            itemView.dimentions.text = "${item.weight}, ${item.size}"
            itemView.addToCart.text = "${item.price} usd"
            itemView.addToCart.setOnClickListener {
                onAddToCartClicked(item.id)
                itemView.addToCart.visibility = View.GONE;
                itemView.addedToCart.visibility = View.VISIBLE;
                itemView.addedToCart.animate()
                    .alpha(1.0f)
                    .setListener(object :Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {

                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            itemView.addToCart.visibility = View.VISIBLE
                            itemView.addedToCart.visibility = View.GONE
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationRepeat(animation: Animator?) {
                        }

                    });
            }
        }
    }

}
