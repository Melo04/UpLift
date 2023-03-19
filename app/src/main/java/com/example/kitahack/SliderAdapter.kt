package com.example.kitahack

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import com.example.kitahack.R
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

internal class SliderAdapter(var context: Context) : PagerAdapter() {
    var layoutInflater: LayoutInflater? = null
    var images = intArrayOf(
        R.drawable.jobs,
        R.drawable.b40,
        R.drawable.shop,
        R.drawable.bg
    )
    var headings = intArrayOf(
        R.string.first_slide_title,
        R.string.two_slide_title,
        R.string.three_slide_title,
        R.string.four_slide_title
    )
    var descriptions = intArrayOf(
        R.string.first_slide_desc,
        R.string.two_slide_desc,
        R.string.three_slide_desc,
        R.string.four_slide_desc
    )

    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.slides_layout, container, false)
        val imageView = view.findViewById<ImageView>(R.id.slider_image)
        val heading = view.findViewById<TextView>(R.id.slider_heading)
        val desc = view.findViewById<TextView>(R.id.slider_desc)
        imageView.setImageResource(images[position])
        heading.setText(headings[position])
        desc.setText(descriptions[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}