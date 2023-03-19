package com.example.kitahack

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kitahack.adapter.PopularFoodAdapterClass
import com.example.kitahack.model.ManagementCart

class DetailsActivity : AppCompatActivity() {
    var picFood: ImageView? = null
    var plusBtn: ImageView? = null
    var minusBtn: ImageView? = null
    var addToCartBtn: Button? = null
    var titleTxt: TextView? = null
    var priceTxt: TextView? = null
    var descriptionTxt: TextView? = null
    var numberOrderTxt: TextView? = null
    var numberOrder = 1
    private var managementCart: ManagementCart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        managementCart = ManagementCart(this)
        initView();
        getBundle();
    }

    @SuppressLint("SetTextI18n")
    private fun getBundle(){
        val picFood: ImageView = findViewById(R.id.picFood)
        val obj = intent.getSerializableExtra("object") as PopularFoodAdapterClass

        Glide.with(this)
            .load(obj.pic)
            .into(picFood)

        titleTxt?.text = obj.title
        priceTxt?.text = ("RM" + obj.fee)
        descriptionTxt?.text = obj.description
        numberOrderTxt?.text = numberOrder.toString()

        plusBtn?.setOnClickListener {
            numberOrder++
            numberOrderTxt?.text = numberOrder.toString()
        }

        minusBtn?.setOnClickListener {
            if(numberOrder > 1){
                numberOrder--
            }
            numberOrderTxt?.text = numberOrder.toString()
        }

        addToCartBtn?.setOnClickListener {
            obj.numberInCart = numberOrder
            managementCart?.insertFood(obj)
        }
    }

    private fun initView(){
        addToCartBtn = findViewById(R.id.applyNowButton)
        titleTxt = findViewById(R.id.requirementsTxt)
        priceTxt = findViewById(R.id.priceTxt)
        descriptionTxt = findViewById(R.id.requirement)
        numberOrderTxt = findViewById(R.id.numberOrderTxt)
        plusBtn = findViewById(R.id.plusBtn)
        minusBtn = findViewById(R.id.minusBtn)
        picFood = findViewById(R.id.picFood)
    }

    fun callDashboardPage(view: View) {
        startActivity(Intent(this, Dashboard::class.java))
        finish()
    }
}