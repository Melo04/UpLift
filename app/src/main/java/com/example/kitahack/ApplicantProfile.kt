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

class ApplicantProfile : AppCompatActivity() {
    private val CHANNEL_ID = "com.example.kitahack"
    private val notificationId = 101
    private lateinit var apply_button: Button
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicant_profile)
        apply_button = findViewById(R.id.apply_button)
        val userName : TextView = findViewById(R.id.name)
        val userGender : TextView = findViewById(R.id.gender)
        val userIdentity : TextView = findViewById(R.id.identity)
        val userMobile : TextView = findViewById(R.id.mobile)
        val userEmail : TextView = findViewById(R.id.email)
        val fullName : TextView = findViewById(R.id.full_names)
        val fullEmail : TextView = findViewById(R.id.full_email)
        val userSkills : TextView = findViewById(R.id.skills)

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

        createNotificationChannel()
        apply_button.setOnClickListener{
            sendNotification()
        }

        val sharedPreferences2 = applicationContext.getSharedPreferences("USER_SKILLS", Context.MODE_PRIVATE)
        val skills = sharedPreferences2.getString("skills", "")
        val skillList = skills?.substring(1, skills.length - 1)?.split(", ") // remove brackets and split string into array
        val formattedSkills = skillList?.joinToString("\n") // join array with line breaks
        userSkills.setText(formattedSkills)
    }

    fun callBackPage(view: View) {
        startActivity(Intent(this, JobsPage::class.java))
        finish()

        createNotificationChannel()
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
        val intent = Intent(this, notifications_home::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_logo)
        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_logo)

        val sharedPreferences = applicationContext.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle("Notification")
            .setContentText("Example description")
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigTextStyle().bigText("$name has applied for your job"))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }
}