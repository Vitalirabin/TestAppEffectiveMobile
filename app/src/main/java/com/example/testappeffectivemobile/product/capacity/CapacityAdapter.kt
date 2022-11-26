package com.example.testappeffectivemobile.product.capacity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.with
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R

val diffCallback = CapacityDiffUtil()

class CapacityAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<CapacityItemModel, CapacityAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.capacity_text_view)
        val imageBackground = itemView.findViewById<ImageView>(R.id.capacity_item_image)
        fun bind(
            capacity: CapacityItemModel,
            onClickListener: ItemOnClickListener
        ) {
            if (capacity.isSelect) {
                text.setTextColor(Color(255, 255, 255).toArgb())
                with(imageBackground).load(R.drawable.filter_done_background)
                    .into(imageBackground)
            } else {
                text.setTextColor(Color(141, 141, 141).toArgb())
                with(imageBackground).load(R.drawable.capacity_unselect).into(imageBackground)
            }
            text.text = String.format("%s Gb", capacity.capacity)
            itemView.setOnClickListener { onClickListener.onClick(capacity.capacity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_capacity_product_details, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val capasity = getItem(position)
        holder.bind(capasity, onClickListener)
    }

}