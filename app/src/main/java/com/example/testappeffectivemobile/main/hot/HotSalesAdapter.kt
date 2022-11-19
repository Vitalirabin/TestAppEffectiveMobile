package com.example.testappeffectivemobile.main.hot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R

val diffCallback = HotSalesDiffUtil()

class HotSalesAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<HotSalesModel, HotSalesAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val newImageView = itemView.findViewById<ImageView>(R.id.new_image_view)
        fun bind(
            hotSalesModel: HotSalesModel,
            onClickListener: ItemOnClickListener
        ) {
            if (hotSalesModel.is_new) {
                newImageView.visibility = View.VISIBLE
            }else newImageView.visibility = View.INVISIBLE
            itemView.setOnClickListener { onClickListener.onClick(hotSalesModel.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hot_sales, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hotSalesModel = getItem(position)
        holder.bind(hotSalesModel, onClickListener)
    }

}