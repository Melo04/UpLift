package com.example.kitahack

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kitahack.adapter.PopularJobsAdapterClass
import com.makeramen.roundedimageview.RoundedImageView

class DetailsActivity3 : AppCompatActivity() {
    var picFood: RoundedImageView? = null
    var titleTxt: TextView? = null
    var locationTxt: TextView? = null
    var salaryTxt: TextView? = null
    var timeTxt: TextView? = null
    var areaTxt: TextView? = null
    var descriptionsTxt: TextView? = null
    var requirement: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_jobs_detail)

        initView();
        getBundle();
    }

    @SuppressLint("SetTextI18n")
    private fun getBundle(){
        val picFood: RoundedImageView = findViewById(R.id.picFood)
        val obj = intent.getSerializableExtra("object") as PopularJobsAdapterClass
        Glide.with(this)
            .load(obj.pic)
            .into(picFood)

        titleTxt?.text = obj.title
        locationTxt?.text = obj.location
        salaryTxt?.text = obj.salary
        descriptionsTxt?.text = obj.description
        timeTxt?.text = obj.time
        areaTxt?.text = obj.area
        requirement?.text = obj.requirement

        val iconLocation = findViewById<RoundedImageView>(R.id.iconLocation)

        iconLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("address", obj.location)
            startActivity(intent)
        }
    }

    private fun initView(){
        titleTxt = findViewById(R.id.title)
        locationTxt = findViewById(R.id.locationTxt)
        salaryTxt = findViewById(R.id.salaryTxt)
        timeTxt = findViewById(R.id.addressTxt)
        areaTxt = findViewById(R.id.areaTxt)
        descriptionsTxt = findViewById(R.id.descriptionsTxt)
        picFood = findViewById(R.id.picFood)
        requirement = findViewById(R.id.requirement)
    }

    fun callJobsPage(view: View) {
        startActivity(Intent(this, JobsPage::class.java))
        finish()
    }

    fun callApplyPage(view: View) {
        startActivity(Intent(this, ApplicantProfile::class.java))
        finish()
    }
}