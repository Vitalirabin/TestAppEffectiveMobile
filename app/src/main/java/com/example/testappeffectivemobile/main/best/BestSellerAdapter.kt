package com.example.testappeffectivemobile.main.best

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R

val diffCallback = BestSellerDiffUtils()

class BestSellerAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<BestSellerModel, BestSellerAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.item_best_seller_image_view)
        val title = itemView.findViewById<TextView>(R.id.item_beast_seller_name_text_view)
        val prise = itemView.findViewById<TextView>(R.id.prise_text_view)
        val discountPrise = itemView.findViewById<TextView>(R.id.discount_price_text_view)
        val like = itemView.findViewById<ImageButton>(R.id.like_image_button)
        fun bind(
            beastSellerModel: BestSellerModel,
            onClickListener: ItemOnClickListener
        ) {
            Glide.with(imageView).load(beastSellerModel.picture).centerCrop().into(imageView)
            title.text = beastSellerModel.title
            prise.text = String.format("$%s", beastSellerModel.discount_price)
            prise.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            discountPrise.text =
                String.format("$%s", beastSellerModel.price_without_discount)
            if (beastSellerModel.is_favorites)
                Glide.with(itemView).load(R.drawable.vector_like).into(like)
            like.setOnClickListener { onClickListener.onClick(beastSellerModel.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_best_seller, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hotSalesModel = getItem(position)
        holder.bind(hotSalesModel, onClickListener)
    }

}