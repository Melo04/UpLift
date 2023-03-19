package com.example.kitahack

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kitahack.adapter.PopularCoursesAdapterClass2
import com.makeramen.roundedimageview.RoundedImageView

class course2 : AppCompatActivity() {
    var picFood: RoundedImageView? = null
    var titleTxt: TextView? = null
    var teacherTxt: TextView? = null
    var totalTxt: TextView? = null
    var lesson1: TextView? = null
    var lesson2: TextView? = null
    var lesson3: TextView? = null
    var lesson4: TextView? = null
    var lesson5: TextView? = null
    var lesson6: TextView? = null
    var ratings: TextView? = null
    var description: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course2)

        initView()
        getBundle()
    }

    @SuppressLint("SetTextI18n")
    private fun getBundle(){
        val picFood: RoundedImageView = findViewById(R.id.picFood)
        val obj = intent.getSerializableExtra("object") as PopularCoursesAdapterClass2
        val drawableResourceId = resources.getIdentifier(obj.pic, "drawable", packageName)
        Glide.with(this)
            .load(drawableResourceId)
            .into(picFood)

        titleTxt?.text = obj.title
        teacherTxt?.text = obj.teacher
        totalTxt?.text = obj.total
        lesson1?.text = obj.lesson1
        lesson2?.text = obj.lesson2
        lesson3?.text = obj.lesson3
        lesson4?.text = obj.lesson4
        lesson5?.text = obj.lesson5
        lesson6?.text = obj.lesson6
        ratings?.text = obj.ratings
        description?.text = obj.description

        val button = findViewById<TextView>(R.id.enrollButton)
        button.setOnClickListener {
            val intent = Intent(this, lessontwo::class.java)
            intent.putExtra("object", obj)
            startActivity(intent)
        }
    }

    private fun initView(){
        titleTxt = findViewById(R.id.title)
        teacherTxt = findViewById(R.id.teacher)
        totalTxt = findViewById(R.id.total)
        lesson1 = findViewById(R.id.lesson1)
        lesson2 = findViewById(R.id.lesson2)
        lesson3 = findViewById(R.id.lesson3)
        lesson4 = findViewById(R.id.lesson4)
        lesson5 = findViewById(R.id.lesson5)
        lesson6 = findViewById(R.id.lesson6)
        ratings = findViewById(R.id.ratings)
        description = findViewById(R.id.description)
        picFood = findViewById(R.id.picFood)
    }

    fun callEnrollPage(view: View) {
        startActivity(Intent(this, lessontwo::class.java))
        finish()
    }

    fun callEduPage(view: View) {
        startActivity(Intent(this, education::class.java))
        finish()
    }

}