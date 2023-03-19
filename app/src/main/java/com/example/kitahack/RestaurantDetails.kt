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
import com.example.kitahack.model.ManagementCart3
import com.example.kitahack.model.RestaurantModel

class RestaurantDetails : AppCompatActivity() {
    var picFood: ImageView? = null
    var plusBtn: ImageView? = null
    var minusBtn: ImageView? = null
    var addToCartBtn: Button? = null
    var titleTxt: TextView? = null
    var priceTxt: TextView? = null
    var categoryTxt: TextView? = null
    var descriptionTxt: TextView? = null
    var addressTxt: TextView? = null
    var numberOrderTxt: TextView? = null
    var numberOrder = 1
    private var managementCart3: ManagementCart3? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_details)

        managementCart3 = ManagementCart3(this)
        initView();
        getBundle();
    }

    @SuppressLint("SetTextI18n")
    private fun getBundle(){
        val picFood: ImageView = findViewById(R.id.picFood)
        val obj = intent.getSerializableExtra("object") as RestaurantModel
        val searchBtn : Button = findViewById(R.id.search_btn)
        Glide.with(this)
            .load(obj.pic)
            .into(picFood)

        titleTxt?.text = obj.title
        categoryTxt?.text = obj.category
        priceTxt?.text = ("RM" + obj.fee)
        addressTxt?.text = obj.address
        descriptionTxt?.text = obj.description
        numberOrderTxt?.text = numberOrder.toString()

        searchBtn.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("name", obj.title)
            intent.putExtra("address", obj.address)
            startActivity(intent)
        }

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
            managementCart3?.insertFood(obj)
        }
    }

    private fun initView(){
        addToCartBtn = findViewById(R.id.applyNowButton)
        titleTxt = findViewById(R.id.requirementsTxt)
        priceTxt = findViewById(R.id.priceTxt)
        descriptionTxt = findViewById(R.id.requirement)
        categoryTxt = findViewById(R.id.categoryTxt)
        numberOrderTxt = findViewById(R.id.numberOrderTxt)
        addressTxt = findViewById(R.id.addressTxt)
        plusBtn = findViewById(R.id.plusBtn)
        minusBtn = findViewById(R.id.minusBtn)
        picFood = findViewById(R.id.picFood)
    }

    fun callMarketPage(view: View) {
        startActivity(Intent(this, marketplace::class.java))
        finish()
    }
}