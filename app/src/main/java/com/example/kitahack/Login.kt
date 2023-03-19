package com.example.kitahack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kitahack.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupRedirectText.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener{
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        database = FirebaseDatabase.getInstance().getReference("Username")
                        val userId = FirebaseAuth.getInstance().currentUser!!.uid
                        val checkUser = database.orderByChild("userEmail").equalTo(email)
                        checkUser.addValueEventListener(object: ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                var emailExists = false
                                var correctPassword = false
                                for (childSnapshot in snapshot.children) {
                                    val emailFromDb = childSnapshot.child("userEmail").getValue(String::class.java)
                                    if (emailFromDb == email) {
                                        emailExists = true
                                        val passwordFromDb = childSnapshot.child("userPassword").getValue(String::class.java)
                                        if (passwordFromDb == password) {
                                            correctPassword = true
                                            val nameFromDb = childSnapshot.child("userName").getValue(String::class.java)
                                            val mobileFromDb = childSnapshot.child("userMobile").getValue(String::class.java)
                                            val idFromDb = childSnapshot.child("userId").getValue(String::class.java)
                                            val genderFromDb = childSnapshot.child("userGender").getValue(String::class.java)
                                            val identityFromDb = childSnapshot.child("userIdentity").getValue(String::class.java)
                                            Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_SHORT).show()

                                            val intent = Intent(applicationContext, Dashboard::class.java)
                                            startActivity(intent)

                                            val sharedPreferences = applicationContext.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
                                            val editor = sharedPreferences.edit()
                                            editor.putString("name", nameFromDb)
                                            editor.putString("gender", genderFromDb)
                                            editor.putString("identity", identityFromDb)
                                            editor.putString("email", emailFromDb)
                                            editor.putString("mobile", mobileFromDb)
                                            editor.putString("password", password)
                                            editor.putString("id", idFromDb)
                                            editor.apply()
                                        }
                                        break
                                    }
                                }

                                if (!emailExists) {
                                    binding.loginEmail.error = "No such user exists"
                                    binding.loginEmail.requestFocus()
                                } else if (!correctPassword) {
                                    binding.loginPassword.error = "Incorrect password"
                                    binding.loginPassword.requestFocus()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {

                            }
                        })
                    }else{
                        Toast.makeText(this, "Incorrect password or username", Toast.LENGTH_SHORT).show()
                    }
                }

            }else if(email.isEmpty()){
                binding.loginEmail.error = "Field cannot be empty";
            }else if(password.isEmpty()){
                binding.loginPassword.error = "Field cannot be empty";
            }
        }
    }

    fun callForgetPassword(view: View){
        startActivity(Intent(this, ForgetPassword::class.java))
        finish()
    }
}