package com.example.kitahack

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class RetailerStartUpScreen : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_retailer_start_up_screen)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<MaterialButton>(R.id.googleSignin).setOnClickListener{
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if(account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.fetchSignInMethodsForEmail(account.email!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result?.signInMethods?.size ?: 0
                    if (result == 0) {
                        val intent : Intent = Intent(this, SignUp::class.java)
                        intent.putExtra("name", account.displayName)
                        intent.putExtra("email", account.email)
                        intent.putExtra("id", account.id)
                        intent.putExtra("fromRetailer", true)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "This email is already registered", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun signup(view: View?){
        startActivity(Intent(this, SignUp::class.java))
        finish()
    }

    fun login(view: View?){
        startActivity(Intent(this, Login::class.java))
        finish()
    }
}