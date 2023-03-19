package com.example.kitahack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kitahack.databinding.ActivitySignUpBinding
import com.example.kitahack.model.UsernameList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Username")

        binding.loginRedirectText.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        if(intent.getBooleanExtra("fromRetailer", false)) {
            val name2 = intent.getStringExtra("name")
            val email2 = intent.getStringExtra("email")
            binding.signupName.setText(name2)
            binding.signupEmail.setText(email2)
        }

        binding.signupButton.setOnClickListener{
            val name = binding.signupName.text.toString()
            val gender = binding.signupGender.text.toString()
            val identity = binding.signupIdentity.text.toString()
            val mobile = binding.signupMobile.text.toString()
            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()
            val confirmpassword = binding.confirmPassword.text.toString()

            if(name.isNotEmpty() && gender.isNotEmpty() && identity.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()){
                val nameId = database.push().key!!
                if(password == confirmpassword){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, Skills::class.java)
                            intent.putExtra("userid", nameId)
                            intent.putExtra("name", name)
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                    val username = UsernameList(nameId, name, gender, identity, email, mobile, password)
                    database.child(nameId).setValue(username)
                        .addOnCompleteListener{
                            val sharedPreferences = applicationContext.getSharedPreferences("USER_SIGNIN", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("userid", nameId)
                            editor.apply()

                            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                            binding.signupName.text?.clear()
                            binding.signupGender.text?.clear()
                            binding.signupIdentity.text?.clear()
                            binding.signupMobile.text?.clear()
                            binding.signupEmail.text?.clear()
                            binding.signupPassword.text?.clear()
                            binding.confirmPassword.text?.clear()
                        }.addOnFailureListener{
                            Toast.makeText(this, "Error, please make sure data are correctly inserted", Toast.LENGTH_LONG).show()
                        }
                }else{
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else if(name.isEmpty()){
                binding.signupName.error = "Field cannot be empty";
            }else if(gender.isEmpty()){
                binding.signupGender.error = "Field cannot be empty";
            }else if(identity.isEmpty()){
                binding.signupIdentity.error = "Field cannot be empty";
            }else if(mobile.isEmpty()){
                binding.signupMobile.error = "Field cannot be empty";
            }else if(email.isEmpty()){
                binding.signupEmail.error = "Field cannot be empty";
            }else if(password.isEmpty()){
                binding.signupPassword.error = "Field cannot be empty";
            }else if(confirmpassword.isEmpty()){
                binding.confirmPassword.error = "Field cannot be empty";
            }
        }
    }
}