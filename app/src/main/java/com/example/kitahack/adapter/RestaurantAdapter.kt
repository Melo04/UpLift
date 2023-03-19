package com.example.kitahack.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.kitahack.R
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kitahack.RestaurantDetails
import com.example.kitahack.model.RestaurantModel
import com.makeramen.roundedimageview.RoundedImageView
import kotlin.collections.ArrayList

class RestaurantAdapter(var popularFood2: ArrayList<RestaurantModel>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularFood2[position].title
        holder.address.text = popularFood2[position].address
        holder.fee.text = popularFood2[position].fee.toString()

        Glide.with(holder.itemView.context)
            .load(popularFood2[position].pic)
            .into(holder.pic)

        holder.addBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, RestaurantDetails::class.java)
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
        var address: TextView
        var pic: RoundedImageView
        var addBtn: ConstraintLayout

        init {
            title = itemView.findViewById(R.id.title)
            fee = itemView.findViewById(R.id.welcome)
            address = itemView.findViewById(R.id.address)
            pic = itemView.findViewById(R.id.picFood)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}