package com.example.testappeffectivemobile.main.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R

val diffCallback = CategoryDiffUtil()

class CategoryAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<CategoryModel, CategoryAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bacground: View = itemView.findViewById(R.id.category_view)
        val name: TextView = itemView.findViewById(R.id.category_name)
        val image: ImageView = itemView.findViewById(R.id.category_item_image_view)
        fun bind(
            category: CategoryModel,
            onClickListener: ItemOnClickListener
        ) {
            this.name.text = category.name
            if (category.isSelected) {
                this.bacground.background = category.selectBackground
                this.image.setImageDrawable(category.selectIcon)
            } else {
                this.bacground.background = category.background
                this.image.setImageDrawable(category.icon)
            }

            itemView.setOnClickListener { onClickListener.onClick(category.name) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category, onClickListener)
    }

}