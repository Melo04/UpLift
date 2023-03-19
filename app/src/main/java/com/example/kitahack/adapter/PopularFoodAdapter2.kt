package com.example.kitahack.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.kitahack.R
import com.bumptech.glide.Glide
import android.widget.TextView
import com.example.kitahack.DetailsActivity2
import kotlin.collections.ArrayList

class PopularFoodAdapter2(var popularFood2: ArrayList<PopularFoodAdapterClass2>) :
    RecyclerView.Adapter<PopularFoodAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_product, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularFood2[position].title
        holder.category.text = popularFood2[position].category
        holder.fee.text = popularFood2[position].fee.toString()

        Glide.with(holder.itemView.context)
            .load(popularFood2[position].pic)
            .into(holder.pic)

        holder.addBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailsActivity2::class.java)
            intent.putExtra("object", popularFood2[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularFood2.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var fee: TextView
        var pic: ImageView
        var addBtn: TextView
        var category: TextView

        init {
            title = itemView.findViewById(R.id.area)
            fee = itemView.findViewById(R.id.fee)
            pic = itemView.findViewById(R.id.picFood)
            category = itemView.findViewById(R.id.time)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}