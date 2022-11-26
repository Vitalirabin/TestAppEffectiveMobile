package com.example.testappeffectivemobile.product.color

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R

val colorDiffCallback = ColorDiffUtil()

class ColorAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<ColorItemModel, ColorAdapter.MyViewHolder>(colorDiffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.check_mark_image_view)
        fun bind(
            colorModel: ColorItemModel,
            onClickListener: ItemOnClickListener
        ) {
            if (colorModel.isCheck) {
                Glide.with(imageView).load(R.drawable.vector_check_mark).into(imageView)
            } else {
                imageView.setImageDrawable(null)
            }
            imageView.setBackgroundColor(Color.parseColor(colorModel.color))
            itemView.setOnClickListener { onClickListener.onClick(colorModel.color) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_color_product_details, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val color = getItem(position)
        holder.bind(color, onClickListener)
    }

}