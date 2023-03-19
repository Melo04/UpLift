package com.example.kitahack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.example.kitahack.databinding.ActivitySkillsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Skills : AppCompatActivity() {
    private lateinit var binding: ActivitySkillsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var userid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkillsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Username")

        val checkbox1 = binding.cookingCheckbox
        val checkbox2 = binding.drivingCheckbox
        val checkbox3 = binding.BilingualismCheckbox
        val checkbox4 = binding.childcareCheckbox
        val checkbox5 = binding.airConditioningCheckbox
        val checkbox6 = binding.customerServiceCheckbox
        val checkbox7 = binding.foodDeliveryCheckbox
        val checkbox8 = binding.manualLabourCheckbox
        val checkbox9 = binding.communicationCheckbox
        val checkbox10 = binding.technicalCheckbox
        val checkbox11 = binding.eldercareCheckbox
        val checkbox12 = binding.foodServiceCheckbox
        val checkbox13 = binding.graphicCheckbox
        val checkbox14 = binding.healthCheckbox
        val checkbox15 = binding.writingCheckbox
        val checkbox16 = binding.sewingCheckbox

        userid = intent.getStringExtra("userid")!!

        binding.continueButton.setOnClickListener {
            val skills = mutableListOf<String>()
            if (checkbox1.isChecked) {
                skills.add("Cooking")
            }
            if (checkbox2.isChecked) {
                skills.add("Driving")
            }
            if (checkbox3.isChecked) {
                skills.add("Bilingualism")
            }
            if (checkbox4.isChecked) {
                skills.add("Childcare")
            }
            if (checkbox5.isChecked) {
                skills.add("Air Conditioning Repair")
            }
            if (checkbox6.isChecked) {
                skills.add("Customer Service")
            }
            if (checkbox7.isChecked) {
                skills.add("Food Delivery")
            }
            if (checkbox8.isChecked) {
                skills.add("Manual Labour")
            }
            if (checkbox9.isChecked) {
                skills.add("Communication")
            }
            if (checkbox10.isChecked) {
                skills.add("Basic Computer Skills")
            }
            if (checkbox11.isChecked) {
                skills.add("Eldercare")
            }
            if (checkbox12.isChecked) {
                skills.add("Food Service")
            }
            if (checkbox13.isChecked) {
                skills.add("Graphic Design")
            }
            if (checkbox14.isChecked) {
                skills.add("Health and Wellness")
            }
            if (checkbox15.isChecked) {
                skills.add("Writing and Editing")
            }
            if (checkbox16.isChecked) {
                skills.add("Sewing or Garment Making")
            }

            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                database.child(userid).child("skills").setValue(skills)
                    .addOnSuccessListener {
                        val sharedPreferences = applicationContext.getSharedPreferences("USER_SKILLS", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("skills", skills.toString())
                        editor.apply()

                        Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, signUp3::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error, please make sure data are correctly inserted", Toast.LENGTH_LONG).show()
                    }
            }
        }

    }

    fun callBackPage(view: View) {
        startActivity(Intent(this, SignUp::class.java))
        finish()
    }
}