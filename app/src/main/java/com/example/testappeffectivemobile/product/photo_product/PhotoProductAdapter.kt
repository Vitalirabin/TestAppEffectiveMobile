package com.example.testappeffectivemobile.product.photo_product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R

val photoDiffCallback = PhotoDiffUtil()

class PhotoProductAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<String, PhotoProductAdapter.MyViewHolder>(photoDiffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.product_item_image_view)
        fun bind(
            photoUrl: String,
            onClickListener: ItemOnClickListener
        ) {
            Glide.with(imageView).load(photoUrl).centerCrop().into(imageView)
            itemView.setOnClickListener { onClickListener.onClick(photoUrl) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_details, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photoUrl = getItem(position)
        holder.bind(photoUrl, onClickListener)
    }

}