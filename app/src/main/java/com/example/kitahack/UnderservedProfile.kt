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
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class UnderservedProfile : AppCompatActivity() {
    private val CHANNEL_ID = "com.example.kitahack"
    private val notificationId = 101
    private lateinit var apply_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_underserved_profile)
        apply_button = findViewById(R.id.apply_button)
        val userName : TextView = findViewById(R.id.name)
        val userGender : TextView = findViewById(R.id.gender)
        val userIdentity : TextView = findViewById(R.id.identity)
        val userMobile : TextView = findViewById(R.id.mobile)
        val userEmail : TextView = findViewById(R.id.email)
        val fullName : TextView = findViewById(R.id.full_names)
        val fullEmail : TextView = findViewById(R.id.full_email)
        val userSkills : TextView = findViewById(R.id.skills)

        createNotificationChannel()
        apply_button.setOnClickListener{
            sendNotification()
        }

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

    fun callNotifPage(view: View) {
        startActivity(Intent(this, notifications_home::class.java))
        finish()
    }
}