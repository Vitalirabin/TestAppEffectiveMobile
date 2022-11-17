package com.example.testappeffectivemobile.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R

val diffCallback = CategoryDiffUtil()

class MainAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<CategoryModel, MainAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: View = itemView.findViewById(R.id.category_view)
        val name: TextView = itemView.findViewById(R.id.category_name)
        fun bind(
            category: CategoryModel,
            onClickListener: ItemOnClickListener
        ) {
            this.name.text = category.name
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
        val match = getItem(position)
        holder.bind(match, onClickListener)
    }

}