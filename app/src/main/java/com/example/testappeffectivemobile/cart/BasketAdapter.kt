package com.example.testappeffectivemobile.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R

val diffCallback = CartDiffUtil()

class BasketAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<BasketModel, BasketAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.item_cart_image_view)
        val title = itemView.findViewById<TextView>(R.id.item_cart_title_text_view)
        val prise = itemView.findViewById<TextView>(R.id.item_cart_prise_text_view)
        val count = itemView.findViewById<TextView>(R.id.item_cart_count)
        val plusButton = itemView.findViewById<ImageView>(R.id.button_plus_cart_item_count)
        val minusButton = itemView.findViewById<ImageView>(R.id.button_minus_cart_item_count)
        fun bind(
            basket: BasketModel,
            onClickListener: ItemOnClickListener
        ) {
            Glide.with(imageView).load(basket.images).centerCrop().into(imageView)
            title.text = basket.title
            count.text = basket.count.toString()
            prise.text = String.format(
                "$%s",
                (basket.price.toInt() * count.text.toString().toInt()).toString()
            ).toString()
            plusButton.setOnClickListener { onClickListener.onClickPlus(basket) }
            minusButton.setOnClickListener { onClickListener.onClickMinus(basket) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val basket = getItem(position)
        holder.bind(basket, onClickListener)
    }

}