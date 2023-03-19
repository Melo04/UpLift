package com.example.kitahack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class userSignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_sign_in)
    }

    fun callRetailerPage(view: View) {
        startActivity(Intent(this, RetailerStartUpScreen::class.java))
        finish()
    }
}