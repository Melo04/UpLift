package com.example.kitahack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ForgetPasswordSuccessMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password_success_message)
    }

    fun callPasswordScreen(view: View){
        startActivity(Intent(this, ForgetPassword::class.java))
        finish()
    }

    fun callLoginScreen(view: View){
        startActivity(Intent(this, Login::class.java))
        finish()
    }
}