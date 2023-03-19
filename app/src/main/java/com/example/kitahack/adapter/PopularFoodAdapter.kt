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
import com.example.kitahack.DetailsActivity
import kotlin.collections.ArrayList

class PopularFoodAdapter(var popularFood: ArrayList<PopularFoodAdapterClass>) :
    RecyclerView.Adapter<PopularFoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_popular, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularFood[position].title
        holder.fee.text = popularFood[position].fee.toString()

        Glide.with(holder.itemView.context)
            .load(popularFood[position].pic)
            .into(holder.pic)

        holder.addBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("object", popularFood[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularFood.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var fee: TextView
        var pic: ImageView
        var addBtn: TextView

        init {
            title = itemView.findViewById(R.id.title)
            fee = itemView.findViewById(R.id.fee)
            pic = itemView.findViewById(R.id.picFood)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}