package com.nourtayeb.dindinn.presentation.main_products.ui.adapter

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nourtayeb.dindinn.R
import com.nourtayeb.dindinn.domain.entity.Promotion
import com.nourtayeb.dindinn.common.loadImageFromUrl
import com.smarteist.autoimageslider.SliderViewAdapter;
import kotlinx.android.synthetic.main.slider_item.view.*

class SliderAdapterExample(val context: Context) :
    SliderViewAdapter<SliderAdapterExample.SliderAdapterVH>() {

    val mSliderItems = mutableListOf<Promotion>();


    fun addItems(list: List<Promotion>) {
        if (mSliderItems != list) {
            this.mSliderItems.addAll(list)
            notifyDataSetChanged();
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent?.getContext()).inflate(R.layout.slider_item, null);
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        viewHolder?.bind(mSliderItems.get(position))
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {

        fun bind(item: Promotion) {

            itemView.image?.loadImageFromUrl(item.image)
        }
    }
}