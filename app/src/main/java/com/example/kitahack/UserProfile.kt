package com.example.kitahack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserProfile : AppCompatActivity() {
    private lateinit var name: String
    private lateinit var gender: String
    private lateinit var identity: String
    private lateinit var email: String
    private lateinit var phoneNo: String
    private lateinit var password: String
    private lateinit var userid: String
    private lateinit var nameProfile: TextInputEditText
    private lateinit var genderProfile: TextInputEditText
    private lateinit var identityProfile: TextInputEditText
    private lateinit var emailProfile: TextInputEditText
    private lateinit var mobileProfile: TextInputEditText
    private lateinit var passwordProfile:TextInputEditText
    private lateinit var fullNameProfile: TextView
    private lateinit var fullEmailProfile: TextView
    private lateinit var database: DatabaseReference
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        database = FirebaseDatabase.getInstance().getReference("Username")

        nameProfile = findViewById(R.id.name_profile)
        genderProfile = findViewById(R.id.gender_profile)
        identityProfile = findViewById(R.id.identity_profile)
        emailProfile = findViewById(R.id.email_profile)
        mobileProfile = findViewById(R.id.mobile_profile)
        passwordProfile = findViewById(R.id.password_profile)
        fullNameProfile = findViewById(R.id.full_names)
        fullEmailProfile = findViewById(R.id.full_email)
        val updateBtn : Button = findViewById(R.id.update_btn)

        updateBtn.setOnClickListener {
            update()
        }

        val sharedPreferences = applicationContext.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        name = sharedPreferences.getString("name", "").toString()
        email = sharedPreferences.getString("email", "").toString()
        gender = sharedPreferences.getString("gender", "").toString()
        identity = sharedPreferences.getString("identity", "").toString()
        phoneNo = sharedPreferences.getString("mobile", "").toString()
        password = sharedPreferences.getString("password", "").toString()
        userid = sharedPreferences.getString("id", "").toString()
        fullNameProfile.setText(name)
        fullEmailProfile.setText(email)
        nameProfile.setText(name)
        genderProfile.setText(gender)
        identityProfile.setText(identity)
        emailProfile.setText(email)
        mobileProfile.setText(phoneNo)
        passwordProfile.setText(password)
    }

    fun update(){
        if(isDataChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Data cannot be updated", Toast.LENGTH_LONG).show();
        }
    }

    private fun isDataChanged(): Boolean {
        if(name != nameProfile.text.toString()){
            database.child(userid).child("userName").setValue(nameProfile.editableText.toString())
            name = nameProfile.editableText.toString()
            return true
        }else if(gender != genderProfile.text.toString()){
            database.child(userid).child("userGender").setValue(genderProfile.editableText.toString())
            gender = genderProfile.editableText.toString()
            return true
        }else if(identity != identityProfile.text.toString()){
            database.child(userid).child("userIdentity").setValue(identityProfile.editableText.toString())
            identity = identityProfile.editableText.toString()
            return true
        }else if(email != emailProfile.text.toString()){
            database.child(userid).child("userEmail").setValue(emailProfile.editableText.toString())
            email = emailProfile.editableText.toString()
            return true
        }else if(phoneNo != mobileProfile.text.toString()){
            database.child(userid).child("userMobile").setValue(mobileProfile.editableText.toString())
            phoneNo = mobileProfile.editableText.toString()
            return true
        }else if(password != passwordProfile.text.toString()){
            database.child(userid).child("userPassword").setValue(passwordProfile.editableText.toString())
            password = passwordProfile.editableText.toString()
            return true
        }else{
            return false
        }
    }

    fun callProfilePage(view: View) {
        startActivity(Intent(this, UserProfileLogIn::class.java))
        finish()
    }
}