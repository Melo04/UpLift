package com.example.kitahack.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.kitahack.R
import com.bumptech.glide.Glide
import android.widget.TextView
import com.example.kitahack.DetailsActivity3
import com.makeramen.roundedimageview.RoundedImageView
import kotlin.collections.ArrayList

class PopularJobsAdapter(var popularJobs: ArrayList<PopularJobsAdapterClass>) :
    RecyclerView.Adapter<PopularJobsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.popular_jobs_row_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularJobs[position].title
        holder.location.text = popularJobs[position].area
        holder.salary.text = popularJobs[position].salary

        Glide.with(holder.itemView.context)
            .load(popularJobs[position].pic)
            .into(holder.pic)

        holder.addBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailsActivity3::class.java)
            intent.putExtra("object", popularJobs[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularJobs.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var salary: TextView
        var pic: RoundedImageView
        var location: TextView
        var addBtn: TextView

        init {
            title = itemView.findViewById(R.id.title)
            salary = itemView.findViewById(R.id.salary)
            location = itemView.findViewById(R.id.area)
            pic = itemView.findViewById(R.id.picFood)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}