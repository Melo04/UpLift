package com.example.kitahack

import android.annotation.SuppressLint
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
import android.view.WindowManager
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

class notifications_home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val CHANNEL_ID = "com.example.kitahack"
    private val notificationId = 101
    private lateinit var apply_button: Button
    private lateinit var reject_button: TextView
    private lateinit var newNotif: TextView
    private lateinit var auth : FirebaseAuth

    var navigationView: NavigationView? = null
    var contentView: LinearLayout? = null
    var drawerLayout: DrawerLayout? = null;
    var menuIcon: ImageView? = null;

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_notifications_home)
        apply_button = findViewById(R.id.accept)
        reject_button = findViewById(R.id.reject)
        newNotif = findViewById(R.id.newNotif)
        menuIcon = findViewById<ImageView>(R.id.menu_icon)
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);

        createNotificationChannel()
        apply_button.setOnClickListener{
            sendNotification()
        }
        reject_button.setOnClickListener{
            rejectNotification()
        }

        val sharedPreferences = applicationContext.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        newNotif.setText("$name has applied for your job")

        navigationDrawer();
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply{
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_logo)
        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_logo)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle("Congrats! Application accepted")
            .setContentText("Example description")
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigTextStyle().bigText("Congrats! Park Seo Jun has look at your profile and decide to employ you.\nPark Seo Jun: Please come by to my office at 123 Cheras.com tommorow"))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }

    private fun rejectNotification() {
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_logo)
        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_logo)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle("Application rejected")
            .setContentText("Example description")
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigTextStyle().bigText("We are sorry, but Park Seo Jun from company 123 has decided to reject your application, but there's more opportunities to come. Browse through the available jobs now!"))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }

    fun viewProfile(view: View) {
        startActivity(Intent(this, UnderservedProfile::class.java))
        finish()
    }

    fun callDashboardScreen(item: MenuItem) {
        startActivity(Intent(this, Dashboard::class.java))
        finish()
    }

    fun callProfileScreen(item: MenuItem) {
        startActivity(Intent(this, UserProfile::class.java))
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
        navigationView!!.setCheckedItem(R.id.nav_notif)
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
}