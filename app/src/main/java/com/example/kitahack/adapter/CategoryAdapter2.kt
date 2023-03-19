package com.example.kitahack.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.kitahack.R
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.ArrayList

class CategoryAdapter2(var categoryAdapterClass2: ArrayList<CategoryAdapterClass2>) :
    RecyclerView.Adapter<CategoryAdapter2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_category, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = categoryAdapterClass2[position].getTitle()
        var picUrl = ""
        when (position) {
            0 -> {
                picUrl = "cat_1"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background)
            }
            1 -> {
                picUrl = "burger"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background2)
            }
            2 -> {
                picUrl = "basket"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background3)
            }
            3 -> {
                picUrl = "donut"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background4)
            }
            4 -> {
                picUrl = "ic_1"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background5)
            }
        }
        val drawableResourceId = holder.itemView.context.resources.getIdentifier(
            picUrl,
            "drawable",
            holder.itemView.context.packageName
        )
        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.categoryPic)
    }

    override fun getItemCount(): Int {
        return categoryAdapterClass2.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName: TextView
        var categoryPic: ImageView
        var mainLayout: ConstraintLayout

        init {
            categoryName = itemView.findViewById(R.id.categoryName)
            categoryPic = itemView.findViewById(R.id.categoryPic)
            mainLayout = itemView.findViewById(R.id.mainLayout)
        }
    }
}