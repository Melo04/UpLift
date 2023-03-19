package com.example.kitahack.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.kitahack.R
import com.bumptech.glide.Glide
import android.widget.TextView
import com.example.kitahack.DetailsActivity4
import com.makeramen.roundedimageview.RoundedImageView
import kotlin.collections.ArrayList

class PopularJobsAdapter2(var popularJobs2: ArrayList<PopularJobsAdapterClass2>) :
    RecyclerView.Adapter<PopularJobsAdapter2.ViewHolder>() {

    private lateinit var filteredList: List<PopularJobsAdapterClass2>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recommendjobs, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularJobs2[position].title
        holder.time.text = popularJobs2[position].time
        holder.location.text = popularJobs2[position].location
        holder.description.text = popularJobs2[position].description
        holder.area.text = popularJobs2[position].area
        holder.salary.text = popularJobs2[position].salary

        Glide.with(holder.itemView.context)
            .load(popularJobs2[position].pic)
            .into(holder.pic)

        holder.addBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailsActivity4::class.java)
            intent.putExtra("object", popularJobs2[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularJobs2.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var salary: TextView
        var time: TextView
        var location: TextView
        var description: TextView
        var pic: RoundedImageView
        var area: TextView
        var addBtn: TextView

        init {
            title = itemView.findViewById(R.id.title)
            salary = itemView.findViewById(R.id.salary)
            area = itemView.findViewById(R.id.area)
            time = itemView.findViewById(R.id.time)
            location = itemView.findViewById(R.id.location)
            description = itemView.findViewById(R.id.description)
            pic = itemView.findViewById(R.id.picFood)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}