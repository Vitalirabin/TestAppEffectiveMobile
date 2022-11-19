package com.example.testappeffectivemobile.main.best

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R
import com.example.testappeffectivemobile.main.hot.HotSalesDiffUtil
import com.example.testappeffectivemobile.main.hot.HotSalesModel

val diffCallback = HotSalesDiffUtil()

class BeastSellerAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<HotSalesModel, BeastSellerAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newImageView = itemView.findViewById<ImageView>(R.id.new_image_view)
        val imageView = itemView.findViewById<ImageView>(R.id.product_image_view)
        val title = itemView.findViewById<TextView>(R.id.item_title_text_view)
        val subtitle = itemView.findViewById<TextView>(R.id.item_subtitle_text_view)
        val button = itemView.findViewById<Button>(R.id.buy_now_button)
        fun bind(
            hotSalesModel: HotSalesModel,
            onClickListener: ItemOnClickListener
        ) {
            Glide.with(imageView).load(hotSalesModel.picture).centerCrop().into(imageView)
            if (hotSalesModel.is_new) {
                newImageView.visibility = View.VISIBLE
            } else newImageView.visibility = View.INVISIBLE
            title.text = hotSalesModel.title
            subtitle.text = hotSalesModel.subtitle
            button.setOnClickListener { onClickListener.onClick(hotSalesModel.id) }
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