package com.example.kitahack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager

class OnBoarding : AppCompatActivity() {
    var dotsLayout : LinearLayout? = null
    var letsgetstarted : Button? = null
    var animation : Animation? = null
    private lateinit var dots: Array<TextView?>
    var currentPos = 0
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.on_boarding)

        val viewPager = findViewById<ViewPager>(R.id.slider)
        dotsLayout = findViewById(R.id.dots);
        letsgetstarted = findViewById(R.id.get_started_btn)

        viewPager.setAdapter(SliderAdapter(this))
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener)
    }

    fun skip(view: View?) {
        startActivity(Intent(this, RetailerStartUpScreen::class.java))
        finish()
    }

    fun next(view: View?) {
        viewPager!!.currentItem = currentPos + 1
    }

    private fun addDots(position: Int){
        dots = arrayOfNulls<TextView>(4)
        dotsLayout!!.removeAllViews()
        for(i in dots!!.indices){
            dots[i] = TextView(this)
            dots[i]?.text = Html.fromHtml("&#8826;")
            dots[i]?.textSize = 35f
            dotsLayout?.addView(dots[i]);
        }
        if (dots.size > 0) {
            dots[position]!!.setTextColor(resources.getColor(R.color.purple_200))
        }
    }

    var changeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addDots(position)
            currentPos = position;
            if(position == 0){
                letsgetstarted?.visibility = View.INVISIBLE
            }else if(position == 1){
                letsgetstarted?.visibility = View.INVISIBLE
            }else if(position == 2){
                letsgetstarted?.visibility = View.INVISIBLE
            }else{
                animation = AnimationUtils.loadAnimation(this@OnBoarding, R.anim.bottom_animation)
                letsgetstarted?.animation = animation
                letsgetstarted?.visibility = View.VISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

    public fun callRetailerScreens(view: View?){
        startActivity(Intent(this, userSignIn::class.java))
        finish()
    }
}