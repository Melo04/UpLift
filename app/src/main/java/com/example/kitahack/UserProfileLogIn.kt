package com.example.kitahack

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class UserProfileLogIn : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var database: DatabaseReference
    private lateinit var auth : FirebaseAuth

    var navigationView: NavigationView? = null
    var contentView: LinearLayout? = null
    var drawerLayout: DrawerLayout? = null;
    var menuIcon: ImageView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile_log_in)
        val userName : TextView = findViewById(R.id.name)
        val userGender : TextView = findViewById(R.id.gender)
        val userIdentity : TextView = findViewById(R.id.identity)
        val userMobile : TextView = findViewById(R.id.mobile)
        val userEmail : TextView = findViewById(R.id.email)
        val fullName : TextView = findViewById(R.id.full_names)
        val fullEmail : TextView = findViewById(R.id.full_email)
        val userSkills : TextView = findViewById(R.id.skills)

        menuIcon = findViewById(R.id.menu_icon)
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);

        val sharedPreferences = applicationContext.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val gender = sharedPreferences.getString("gender", "")
        val identity = sharedPreferences.getString("identity", "")
        val mobile = sharedPreferences.getString("mobile", "")
        val email = sharedPreferences.getString("email", "")
        userName.setText(name)
        userGender.setText(gender)
        userIdentity.setText(identity)
        userMobile.setText(mobile)
        userEmail.setText(email)
        fullName.setText(name)
        fullEmail.setText(email)

        val sharedPreferences2 = applicationContext.getSharedPreferences("USER_SKILLS", Context.MODE_PRIVATE)
        val skills = sharedPreferences2.getString("skills", "")
        val skillList = skills?.substring(1, skills.length - 1)?.split(", ") // remove brackets and split string into array
        val formattedSkills = skillList?.joinToString("\n") // join array with line breaks
        userSkills.setText(formattedSkills)

        navigationDrawer()
    }

    fun callDashboardScreen(item: MenuItem) {
        startActivity(Intent(this, Dashboard::class.java))
        finish()
    }

    fun callProfileScreen(item: MenuItem) {
        startActivity(Intent(this, UserProfileLogIn::class.java))
        finish()
    }

    fun callMarketScreen(item: MenuItem) {
        startActivity(Intent(this, marketplace::class.java))
        finish()
    }

    fun callJobScreen(item: MenuItem) {
        startActivity(Intent(this, JobsPage::class.java))
        finish()
    }

    fun callCartScreen(item: MenuItem) {
        startActivity(Intent(this, CartListActivity::class.java))
        finish()
    }

    fun callNotifScreen(item: MenuItem) {
        startActivity(Intent(this, notifications_home::class.java))
        finish()
    }

    fun callEduScreen(item: MenuItem) {
        startActivity(Intent(this, education::class.java))
        finish()
    }

    fun callSplashScreen(item: MenuItem) {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        startActivity(Intent(this, RetailerStartUpScreen::class.java))
        finish()
    }

    private fun navigationDrawer() {
        navigationView!!.bringToFront()
        navigationView!!.setNavigationItemSelectedListener(this)
        navigationView!!.setCheckedItem(R.id.nav_profile)
        menuIcon!!.setOnClickListener {
            if (drawerLayout!!.isDrawerVisible(GravityCompat.START)) drawerLayout!!.closeDrawer(
                GravityCompat.START
            ) else drawerLayout!!.openDrawer(GravityCompat.START)
        }

        animateNavigationDrawer();
    }

    private fun animateNavigationDrawer() {
        drawerLayout!!.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

                val diffScaledOffset = slideOffset * (1 - Dashboard.END_SCALE)
                val offsetScale = 1 - diffScaledOffset
                contentView!!.scaleX = offsetScale
                contentView!!.scaleY = offsetScale

                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff = contentView!!.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                contentView!!.translationX = xTranslation
            }
        })
    }

    override fun onBackPressed() {
        if(drawerLayout!!.isDrawerVisible(GravityCompat.START)){
            drawerLayout!!.closeDrawer(GravityCompat.START);
        }else super.onBackPressed();
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    fun callDashboardPage(view: View) {
        startActivity(Intent(this, Dashboard::class.java))
        finish()
    }

    fun callProfilePage(view: View) {
        startActivity(Intent(this, UserProfileLogIn::class.java))
        finish()
    }

    fun callCartPage(view: View) {
        startActivity(Intent(this, CartListActivity::class.java))
        finish()
    }

    fun callMarketPage(view: View) {
        startActivity(Intent(this, marketplace::class.java))
        finish()
    }

    fun callJobPage(view: View) {
        startActivity(Intent(this, JobsPage::class.java))
        finish()
    }

    fun callUpdatePage(view: View) {
        startActivity(Intent(this, UserProfile::class.java))
        finish()
    }
}