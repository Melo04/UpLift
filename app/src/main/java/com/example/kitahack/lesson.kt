package com.example.kitahack

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import com.example.kitahack.adapter.PopularCoursesAdapterClass

class lesson : AppCompatActivity() {
    var med: MediaController? = null
    var videoView: VideoView? = null

    var titleTxt: TextView? = null
    var teacherTxt: TextView? = null
    var totalTxt: TextView? = null
    var lesson1: TextView? = null
    var lesson2: TextView? = null
    var lesson3: TextView? = null
    var lesson4: TextView? = null
    var lesson5: TextView? = null
    var lesson6: TextView? = null
    var description: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        initView()
        getBundle()

        videoView = findViewById(R.id.video)
        videoView?.setVideoPath("android.resource://" + packageName + "/" + R.raw.investment);
        med = MediaController(this)
        videoView?.setMediaController(med)
        med?.setAnchorView(videoView)
        videoView?.start()
    }

    @SuppressLint("SetTextI18n")
    private fun getBundle(){
        val obj = intent.getSerializableExtra("object") as PopularCoursesAdapterClass

        titleTxt?.text = obj.title
        teacherTxt?.text = obj.teacher
        totalTxt?.text = obj.total
        lesson1?.text = obj.lesson1
        lesson2?.text = obj.lesson2
        lesson3?.text = obj.lesson3
        lesson4?.text = obj.lesson4
        lesson5?.text = obj.lesson5
        lesson6?.text = obj.lesson6
        description?.text = obj.description
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
        description = findViewById(R.id.description)
    }

    fun callEduPage(view: View) {
        startActivity(Intent(this, education::class.java))
        finish()
    }
}