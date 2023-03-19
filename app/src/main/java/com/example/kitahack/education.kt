package com.example.kitahack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitahack.adapter.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class education : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var navigationView: NavigationView? = null
    var contentView: LinearLayout? = null
    var drawerLayout: DrawerLayout? = null
    var menuIcon: ImageView? = null
    private lateinit var auth : FirebaseAuth

    var adapter: PopularCourseAdapter? = null
    var courseRecycler: RecyclerView? = null
    var adapter2: PopularCourseAdapter2? = null
    var lessonsRecycler: RecyclerView? = null
    var categoryRecycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)
        menuIcon = findViewById<ImageView>(R.id.menu_icon)
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);
        courseRecycler = findViewById(R.id.courseRecycler);
        lessonsRecycler = findViewById(R.id.lessonsRecycler);

        val username : TextView = findViewById(R.id.welcome)
        val sharedPreferences = applicationContext.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        username.setText("Welcome \n$name")

        navigationDrawer()
        popularRecycler()
        lessonsRecycler()
    }

    fun popularRecycler(){
        courseRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.HORIZONTAL, false))

        val featuredCourse: ArrayList<PopularCoursesAdapterClass> = ArrayList();
        featuredCourse.add(PopularCoursesAdapterClass("investment", "Investing For Beginners","20 Lessons", "Robertus", "Lesson 1: Global Financial Markets and Instruments", "Lesson 2: Portfolio Selection and Risk Management", "Lesson 3: Investment Strategies and Portfolio Analysis", "Lesson 4: Setting Financial Goals and Assessing Situation", "Lesson 5: Get to know the latest stock market", "Lesson 6: Capstone: Build a Winning Investment Portfolio", "4.5 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse.add(PopularCoursesAdapterClass("insurance","Foundation Course Life Insurance", "30 Lessons", "Malaysian Insurance Institute", "Lesson 1: Introduction to Medical Insurance", "Lesson 2: The Potential and Sales of Medical Insurance in Malaysia", "Lesson 3: Scope of Hospital and Surgical Insurance Benefits", "Lesson  4: Sickness and Injuries and Common Exclusion In Hospital and Surgical Insurance Policies", "Lesson 5: Stakeholders of the Hospital and Surgical Insurance Policies", "Lesson 6: Managed Care Organization / Third Party Administration", "4.3 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse.add(PopularCoursesAdapterClass("finance","Financial Planning For Young Adults", "15 Lessons", "Alan Walker", "Lesson 1: Global Financial Markets and Instruments", "Lesson 2: Portfolio Selection and Risk Management", "Lesson 3: Investment Strategies and Portfolio Analysis", "Lesson 4: Setting Financial Goals and Assessing Situation", "Lesson 5: Get to know the latest stock market", "Lesson 6: Capstone: Build a Winning Investment Portfolio", "4.8 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse.add(PopularCoursesAdapterClass("investment","Islamic Insurance (Takaful)", "20 Lessons", "Peter Parker", "Lesson 1: Global Financial Markets and Instruments", "Lesson 2: Portfolio Selection and Risk Management", "Lesson 3: Investment Strategies and Portfolio Analysis", "Lesson 4: Setting Financial Goals and Assessing Situation", "Lesson 5: Get to know the latest stock market", "Lesson 6: Capstone: Build a Winning Investment Portfolio", "4.7 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse.add(PopularCoursesAdapterClass("insurance","Entrepreneurship&Innovation", "20 Lessons", "Peter Parker", "Lesson 1: The Business of Platforms, Networks, and Two-sided Markets", "Lesson 2: Real Estate Primary Markets", "Lesson 3: New Venture Financing", "Lesson 4: Collaboration, Conflict, and Negotiation", "Lesson 5: Managing the Growing Company", "Lesson 6: Managing Family Businesses and Privately Held Firms", "4.2 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse.add(PopularCoursesAdapterClass("finance","Financial Markets & Instrument", "20 Lessons", "Peter Parker", "Lesson 1: Development of Financial Institutions and Markets", "Lesson 2: Financial Service Industry", "Lesson 3: Investment Strategies", "Lesson 4: Debt Instruments and Markets", "Lesson 5: Emerging Financial Markets", "Lesson 6: Managing Investment Funds", "4.3 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))


        adapter = PopularCourseAdapter(featuredCourse);
        courseRecycler?.adapter = adapter;
    }

    fun lessonsRecycler(){
        lessonsRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

        val featuredCourse2: ArrayList<PopularCoursesAdapterClass2> = ArrayList();
        featuredCourse2.add(PopularCoursesAdapterClass2("investment", "Investing For Complete Beginners","20 Lessons", "Robertus", "Lesson 1: Global Financial Markets and Instruments", "Lesson 2: Portfolio Selection and Risk Management", "Lesson 3: Investment Strategies and Portfolio Analysis", "Lesson 4: Setting Financial Goals and Assessing Situation", "Lesson 5: Get to know the latest stock market", "Lesson 6: Capstone: Build a Winning Investment Portfolio", "4.5 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse2.add(PopularCoursesAdapterClass2("insurance","Foundation Course Life Insurance", "30 Lessons", "Malaysian Insurance", "Lesson 1: Global Financial Markets and Instruments", "Lesson 2: Portfolio Selection and Risk Management", "Lesson 3: Investment Strategies and Portfolio Analysis", "Lesson 4: Setting Financial Goals and Assessing Your Situation", "Lesson 5: Get to know the latest stock market", "Lesson 6: Capstone: Build a Winning Investment Portfolio", "4.3 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse2.add(PopularCoursesAdapterClass2("finance","Financial Planning For Young Adults", "15 Lessons", "Alan Walker", "Lesson 1: Global Financial Markets and Instruments", "Lesson 2: Portfolio Selection and Risk Management", "Lesson 3: Investment Strategies and Portfolio Analysis", "Lesson 4: Setting Financial Goals and Assessing Situation", "Lesson 5: Get to know the latest stock market", "Lesson 6: Capstone: Build a Winning Investment Portfolio", "4.8 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse2.add(PopularCoursesAdapterClass2("investment","Islamic Insurance (Takaful)", "20 Lessons", "Peter Parker", "Lesson 1: Global Financial Markets and Instruments", "Lesson 2: Portfolio Selection and Risk Management", "Lesson 3: Investment Strategies and Portfolio Analysis", "Lesson 4: Setting Financial Goals and Assessing Situation", "Lesson 5: Get to know the latest stock market", "Lesson 6: Capstone: Build a Winning Investment Portfolio", "4.7 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse2.add(PopularCoursesAdapterClass2("insurance","Entrepreneurship&Innovation", "20 Lessons", "Peter Parker", "Lesson 1: The Business of Platforms, Networks, and Two-sided Markets", "Lesson 2: Real Estate Primary Markets", "Lesson 3: New Venture Financing", "Lesson 4: Collaboration, Conflict, and Negotiation", "Lesson 5: Managing the Growing Company", "Lesson 6: Managing Family Businesses and Privately Held Firms", "4.2 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))
        featuredCourse2.add(PopularCoursesAdapterClass2("finance","Financial Markets & Instrument", "20 Lessons", "Peter Parker", "Lesson 1: Development of Financial Institutions and Markets", "Lesson 2: Financial Service Industry", "Lesson 3: Investment Strategies", "Lesson 4: Debt Instruments and Markets", "Lesson 5: Emerging Financial Markets", "Lesson 6: Managing Investment Funds", "4.3 Ratings", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."))

        adapter2 = PopularCourseAdapter2(featuredCourse2);
        lessonsRecycler?.adapter = adapter2;
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

    fun callProfilePage(view: View) {
        startActivity(Intent(this, UserProfileLogIn::class.java))
        finish()
    }

    private fun navigationDrawer() {
        navigationView!!.bringToFront()
        navigationView!!.setNavigationItemSelectedListener(this)
        navigationView!!.setCheckedItem(R.id.nav_edu)
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