package com.example.kitahack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword : AppCompatActivity() {
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnResetPassword: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        etPassword = findViewById(R.id.etEmail)
        btnResetPassword = findViewById(R.id.btnResetPassword)
        auth = FirebaseAuth.getInstance()

        btnResetPassword.setOnClickListener {
            val email: String = etPassword.text.toString().trim { it <= ' ' }
            if(email.isEmpty()){
                Toast.makeText(this@ForgetPassword, "Please enter your email address", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@ForgetPassword, "Password reset link has been sent to your email", Toast.LENGTH_SHORT).show()
                            auth.sendPasswordResetEmail(email)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this@ForgetPassword, "Password has been reset successfully", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(this@ForgetPassword, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                                    }
                                }
                        }else{
                            Toast.makeText(this@ForgetPassword, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }

    fun callSelectionScreen(view: View){
        startActivity(Intent(this, Login::class.java))
        finish()
    }
}