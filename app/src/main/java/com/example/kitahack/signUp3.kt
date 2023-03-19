package com.example.kitahack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.hbb20.CountryCodePicker

class signUp3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up3)
    }

    fun callBackPage(view: View) {
        startActivity(Intent(this, Skills::class.java))
        finish()
    }

    fun callContinuePage(view: View) {
        Toast.makeText(this, "Sign Up successfully", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, Login::class.java))
        finish()
    }
}