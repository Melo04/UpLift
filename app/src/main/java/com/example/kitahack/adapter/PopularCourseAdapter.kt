package com.example.kitahack.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.kitahack.R
import com.bumptech.glide.Glide
import android.widget.TextView
import com.example.kitahack.course
import com.example.kitahack.lesson
import com.makeramen.roundedimageview.RoundedImageView
import kotlin.collections.ArrayList

class PopularCourseAdapter(var popularCourse: ArrayList<PopularCoursesAdapterClass>) :
    RecyclerView.Adapter<PopularCourseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.popular_courses, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularCourse[position].title
        holder.total.text = popularCourse[position].total
        holder.teacher.text = popularCourse[position].teacher

        val drawableResourceId = holder.itemView.context.resources.getIdentifier(
            popularCourse.get(position).pic,
            "drawable",
            holder.itemView.context.packageName
        )
        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.pic)

        holder.addBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, course::class.java)
            intent.putExtra("object", popularCourse[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularCourse.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var total: TextView
        var teacher: TextView
        var pic: RoundedImageView
        var addBtn: TextView

        init {
            title = itemView.findViewById(R.id.title)
            total = itemView.findViewById(R.id.total)
            teacher = itemView.findViewById(R.id.teacher)
            pic = itemView.findViewById(R.id.picFood)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}