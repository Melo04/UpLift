package com.example.kitahack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        val image = findViewById<ImageView>(R.id.imageView2);
        val title = findViewById<TextView>(R.id.textView3);
        val slogan = findViewById<TextView>(R.id.textView4);

        image.startAnimation(topAnim);
        title.startAnimation(bottomAnim);
        slogan.startAnimation(bottomAnim);

        Handler().postDelayed({
            var onBoardingScreen = getSharedPreferences("onBoardingScreen", Context.MODE_PRIVATE)
            var isFirstTime : Boolean = onBoardingScreen.getBoolean("firstTime", true)

            if(isFirstTime){
                var editor = onBoardingScreen.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();

                startActivity(Intent(this@Splash, OnBoarding::class.java))
                finish()
            }else{
                startActivity(Intent(this@Splash, userSignIn::class.java))
                finish()
            }
        }, 5000);
    }

    fun login(view: View) {
        startActivity(Intent(this@Splash,userSignIn::class.java))
        finish()
    }
}