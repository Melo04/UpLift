package com.example.kitahack

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.kitahack.adapter.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import java.lang.Math.abs
import java.util.*
import kotlin.collections.ArrayList

class JobsPage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var recommendedRecycler: RecyclerView? = null
    var jobListRecycler: RecyclerView? = null
    var adapter2: PopularJobsAdapter? = null
    var adapter3: PopularJobsAdapter2? = null
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter
    var navigationView: NavigationView? = null
    var contentView: ConstraintLayout? = null
    var drawerLayout: DrawerLayout? = null
    var menuIcon: ImageView? = null
    var searchView: EditText? = null
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs_page)
        menuIcon = findViewById<ImageView>(R.id.menu_icon)
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);
        recommendedRecycler = findViewById<RecyclerView>(R.id.recommendedRecycler)
        jobListRecycler = findViewById<RecyclerView>(R.id.jobListRecycler)

        init()
        setUpTransformer()
        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })

        navigationDrawer();
        recommendedRecycler()
        jobListRecycler()
    }

    private fun navigationDrawer() {
        navigationView!!.bringToFront()
        navigationView!!.setNavigationItemSelectedListener(this)
        navigationView!!.setCheckedItem(R.id.nav_job)
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

                // Scale the View based on current slide offset
                val diffScaledOffset = slideOffset * (1 - Dashboard.END_SCALE)
                val offsetScale = 1 - diffScaledOffset
                contentView!!.scaleX = offsetScale
                contentView!!.scaleY = offsetScale

                // Translate the View, accounting for the scaled width
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

    fun recommendedRecycler(){
        recommendedRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.HORIZONTAL, false))

        val featuredJobs: ArrayList<PopularJobsAdapterClass> = ArrayList();
        featuredJobs.add(PopularJobsAdapterClass(
            "SPM Tuition teacher 1 month",
            "https://www.tutorkami.com/images/Slider1920x1000.jpg",
            "We are seeking a highly motivated and experienced SPM tuition teacher to teach students in the upcoming SPM. The successful candidate will be responsible for helping students prepare for the Sijil Pelajaran Malaysia (SPM) examination",
            "RM 100 per day",
            "1 month",
            "Cheras",
            "Cheras, Selangor",
            "• Strong subject knowledge and excellent teaching skills\n• Good communication and interpersonal skills\n• Ability to work effectively in a team environment\n• Passion for teaching and helping students succeed"
        ))
        featuredJobs.add(PopularJobsAdapterClass(
            "Food Delivery (now)",
            "https://shipsy.io/wp-content/uploads/2021/07/Blog-64.jpg",
            "Picking up and delivering food orders to customers at Puchong IOI Mall McDonald",
            "RM 10 per delivery",
            "now",
            "IOI Mall McDonald's",
            "Ioi Mall, Lot 17, Ground Floor, Batu 9, Jalan Puchong, Bandar Puchong Jaya, 47100 Puchong, Selangor",
            "• Ensuring timely and accurate delivery of food orders\n• Ensuring that all food orders are properly packaged and delivered in good condition"
        ))
        featuredJobs.add(PopularJobsAdapterClass(
            "Waiter",
            "https://www.jobstreet.com.sg/career-resources/wp-content/uploads/sites/3/2021/08/Waiter-Career-Path.jpg",
            "We are looking for a friendly and hardworking waiter/waitress to join our team. As a waiter, you will be responsible for taking orders, serving food and drinks, ",
            "RM 2K+ - 10K / per month",
            "Full Time",
            "Cheras",
            "Google LLC, Cheras, Petaling Jaya",
            "• No previous experience is required, but prior experience is a plus\n• Must be able to work in a fast-paced environment\n• Must be able to work flexible hours, including weekends and holidays\n• Must be friendly and customer-oriented"
        ))
        featuredJobs.add(PopularJobsAdapterClass(
            "Hairdresser",
            "https://www.simplybusiness.co.uk/static/c140c95b2ac29a2fb643d581e4dc3481/87f10/how-to-become-hairdresser-hero.jpg",
            "We are seeking a skilled and compassionate Hairdresser to join our team and provide hair care services to people",
            "RM 2K+ - 5K / per month",
            "Full Time",
            "Cheras",
            "Google LLC, Cheras, Petaling Jaya",
            "• Knowledge of hair care techniques, products, and trends\n• Ability to communicate effectively with clients and understand their hair care needs and preferences\n• Compassionate and patient demeanor\n• Willingness to work occasional evenings and weekends"
        ))
        featuredJobs.add(
            PopularJobsAdapterClass(
                "Lorry Driver (Immediate Hiring)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Truckdriver.jpg/800px-Truckdriver.jpg",
                "We are seeking a reliable and experienced Lorry Driver responsible for providing safe and comfortable transportation to and from various destinations.",
                "RM 120 per hour",
                "3 days 11/3, 12/3 & 13/3",
                "Cheras - Melacca",
                "452-6, Jalan Bachang Baru 1/3, Taman Bachang Baru, Bachang, 75350, Melaka, Melaka, 75350",
                "• A valid driver's license and a clean driving record\n• Prior experience as a personal or commercial driver\n• Ability to communicate effectively and respectfully with the client\n• Follow traffic rules and regulations to ensure safe driving practices"
            )
        )
        featuredJobs.add(
            PopularJobsAdapterClass(
                "Malay Tuition \nHome Tutor",
                "https://www.teachermagazine.com/assets/images/teacher/_800x418_crop_center-center_82_none/Implementing-tutoring-interventions-in-schools.jpg?mtime=1606961606",
                "One to One Physical Malay Online Home Tutor RM85 per hour Every Thursday and Friday 2pm to 4pm, public holidays double pay",
                "RM 85 per hour",
                "2 months every Friday",
                "Puchong",
                "SERI MEKAR APARMENT D 01 16, 47300 Puchong, Selangor",
                "• Strong subject knowledge and excellent teaching skills\n• Good communication and interpersonal skills\n• Ability to work effectively in a team environment\n• Passion for teaching and helping students succeed"
            )
        )
        featuredJobs.add(
            PopularJobsAdapterClass(
                "Personal Driver(Kuala Lumpur) for 1 week",
                "https://www.tutorkami.com/images/Slider1920x1000.jpg",
                "responsible for providing safe and comfortable transportation to and from various destinations,",
                "RM 2K+ - 3K+ per month",
                "Full Time",
                "Cheras",
                "56-2, Jalan Dwitasik, Dataran Dwitasik, 56000 Cheras, Wilayah Persekutuan Kuala Lumpur",
                "• A valid driver's license and a clean driving record\n• Prior experience as a personal or commercial driver\n• Ability to communicate effectively and respectfully with the client\n• Follow traffic rules and regulations to ensure safe driving practices"
            )
        )

        adapter2 = PopularJobsAdapter(featuredJobs);
        recommendedRecycler?.adapter = adapter2;
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

    fun jobListRecycler() {
        jobListRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

        val featuredJobsList: ArrayList<PopularJobsAdapterClass2> = ArrayList()
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Lorry Driver (4 days)",
                "https://global-uploads.webflow.com/62ab029f12b7d74f665b6cdf/633f2da3cd7826a675e54162_company-driver-job-description-4800x3200-2020123.jpeg",
                "We are seeking a reliable and experienced Lorry Driver responsible for providing safe and comfortable transportation to and from various destinations.",
                "RM 200 per day",
                "4 days",
                "Petaling Jaya",
                "56-2, Jalan Dwitasik, Dataran Dwitasik, 56000 Cheras, Wilayah Persekutuan Kuala Lumpur",
                "• A valid driver's license and a clean driving record\n• Prior experience as a personal or commercial driver\n• Ability to communicate effectively and respectfully with the client\n• Follow traffic rules and regulations to ensure safe driving practices"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Housemaid ( Every Saturday)",
                "https://www.pristinehome.com.au/wp-content/uploads/2020/01/15-Cleaning-Tips-from-Professional-Cleaners.jpg",
                "We are seeking a reliable and hardworking housemaid. As a housemaid, you will be responsible for maintaining cleanliness of our home. You will be responsible for performing various household duties such as cleaning, washing dishes, laundry, and other household chores.",
                "RM 2K+ - 10K / per month",
                "Full Time",
                "Damansara",
                "56-2, Jalan Dwitasik, Dataran Dwitasik, 56000 Cheras, Wilayah Persekutuan Kuala Lumpur",
                "• Perform various cleaning tasks including dusting, sweeping, vacuuming, and mopping\n• No formal education is required, but basic literacy and numeracy skills are preferred\n• Ability to work effectively in a team environment\n• Do laundry and iron clothes, Change bed linens and make beds"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Aiir Conditioning Repair (now)",
                "https://my.ericanfly.com/wp-content/uploads/2021/02/air-cond-service-malaysia-2-1024x682.jpg",
                "Urgent! Need help to repair the aircond in my house, there's water leaking",
                "RM 50",
                "now",
                "Cheras",
                "56-2, Jalan Dwitasik, Dataran Dwitasik, 56000 Cheras, Wilayah Persekutuan Kuala Lumpur",
                "• Aircond technician\n• Ability in help repairing the aircond"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Food Delivery Rider (Shopee Express)",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQly4Jtoo6bjysIg0xKJF3W6fwxd_V7LCZkF_7-mA&s",
                "Responsible for picking up and delivering food orders to customers within a specified area",
                "RM 2K+ - 5K / per month",
                "Fulltime",
                "Selangor",
                "Shopee Express Malaysia Sdn Bhd Selangor",
                "Ensuring timely and accurate delivery of food orders\n• Ensuring that all food orders are properly packaged and delivered in good condition"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Lorry Driver (Immediate Hiring)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Truckdriver.jpg/800px-Truckdriver.jpg",
                "We are seeking a reliable and experienced Lorry Driver responsible for providing safe and comfortable transportation to and from various destinations.",
                "RM 120 per hour",
                "3 days 11/3, 12/3 & 13/3",
                "Cheras - Melacca",
                "452-6, Jalan Bachang Baru 1/3, Taman Bachang Baru, Bachang, 75350, Melaka, Melaka, 75350",
                "• A valid driver's license and a clean driving record\n• Prior experience as a personal or commercial driver\n• Ability to communicate effectively and respectfully with the client\n• Follow traffic rules and regulations to ensure safe driving practices"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Malay Tuition \nHome Tutor",
                "https://www.teachermagazine.com/assets/images/teacher/_800x418_crop_center-center_82_none/Implementing-tutoring-interventions-in-schools.jpg?mtime=1606961606",
                "One to One Physical Malay Online Home Tutor RM85 per hour Every Thursday and Friday 2pm to 4pm, public holidays double pay",
                "RM 85 per hour",
                "2 months every Friday",
                "Puchong",
                "SERI MEKAR APARMENT D 01 16, 47300 Puchong, Selangor",
                "• Strong subject knowledge and excellent teaching skills\n• Good communication and interpersonal skills\n• Ability to work effectively in a team environment\n• Passion for teaching and helping students succeed"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Personal Driver(Kuala Lumpur) for 1 week",
                "https://www.tutorkami.com/images/Slider1920x1000.jpg",
                "responsible for providing safe and comfortable transportation to and from various destinations,",
                "RM 2K+ - 3K+ per month",
                "Full Time",
                "Cheras",
                "56-2, Jalan Dwitasik, Dataran Dwitasik, 56000 Cheras, Wilayah Persekutuan Kuala Lumpur",
                "• A valid driver's license and a clean driving record\n• Prior experience as a personal or commercial driver\n• Ability to communicate effectively and respectfully with the client\n• Follow traffic rules and regulations to ensure safe driving practices"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "SPM Tuition teacher 1 month",
                "https://www.tutorkami.com/images/Slider1920x1000.jpg",
                "We are seeking a highly motivated and experienced SPM tuition teacher to teach students in the upcoming SPM. The successful candidate will be responsible for helping students prepare for the Sijil Pelajaran Malaysia (SPM) examination",
                "RM 85 per hour",
                "Full Time",
                "Cheras",
                "26, Lrg 2B, PJ Seksyen 2C - 2D, 46300 Petaling Jaya, Selangor",
                "• Strong subject knowledge and excellent teaching skills\n• Good communication and interpersonal skills\n• Ability to work effectively in a team environment\n• Passion for teaching and helping students succeed"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Hairdresser",
                "https://www.simplybusiness.co.uk/static/c140c95b2ac29a2fb643d581e4dc3481/87f10/how-to-become-hairdresser-hero.jpg",
                "We are seeking a skilled and compassionate Hairdresser to join our team and provide hair care services to people",
                "RM 2K+ - 5K / per month",
                "Full Time",
                "Cheras",
                "Google LLC, Cheras, Petaling Jaya",
                "• Knowledge of hair care techniques, products, and trends\n• Ability to communicate effectively with clients and understand their hair care needs and preferences\n• Compassionate and patient demeanor\n• Willingness to work occasional evenings and weekends"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Waiter",
                "https://www.jobstreet.com.sg/career-resources/wp-content/uploads/sites/3/2021/08/Waiter-Career-Path.jpg",
                "We are looking for a friendly and hardworking waiter/waitress to join our team. As a waiter, you will be responsible for taking orders, serving food and drinks, ",
                "RM 2K+ - 10K / per month",
                "Full Time",
                "Cheras",
                "Google LLC, Cheras, Petaling Jaya",
                "• No previous experience is required, but prior experience is a plus\n• Must be able to work in a fast-paced environment\n• Must be able to work flexible hours, including weekends and holidays\n• Must be friendly and customer-oriented"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "Food Delivery (now)",
                "https://shipsy.io/wp-content/uploads/2021/07/Blog-64.jpg",
                "Picking up and delivering food orders to customers at Puchong IOI Mall McDonald",
                "RM 10 per delivery",
                "now",
                "IOI Mall McDonald's",
                "Ioi Mall, Lot 17, Ground Floor, Batu 9, Jalan Puchong, Bandar Puchong Jaya, 47100 Puchong, Selangor",
                "• Ensuring timely and accurate delivery of food orders\n• Ensuring that all food orders are properly packaged and delivered in good condition"
            )
        )
        featuredJobsList.add(
            PopularJobsAdapterClass2(
                "SPM Tuition teacher 1 month",
                "https://www.tutorkami.com/images/Slider1920x1000.jpg",
                "We are seeking a highly motivated and experienced SPM tuition teacher to teach students in the upcoming SPM. The successful candidate will be responsible for helping students prepare for the Sijil Pelajaran Malaysia (SPM) examination",
                "RM 100 per day",
                "1 month",
                "Cheras",
                "Cheras, Selangor",
                "• Strong subject knowledge and excellent teaching skills\n• Good communication and interpersonal skills\n• Ability to work effectively in a team environment\n• Passion for teaching and helping students succeed"
            )
        )

        adapter3 = PopularJobsAdapter2(featuredJobsList)
        jobListRecycler?.adapter = adapter3
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer {page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)

        imageList = ArrayList()
        imageList.add(R.drawable.cleaner)
        imageList.add(R.drawable.tuition)
        imageList.add(R.drawable.aircond)
        imageList.add(R.drawable.cleaner)
        imageList.add(R.drawable.tuition)
        imageList.add(R.drawable.aircond)

        adapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
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
}
