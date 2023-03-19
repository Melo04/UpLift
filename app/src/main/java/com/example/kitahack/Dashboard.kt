package com.example.kitahack
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
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

class Dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    var featuredRecycler: RecyclerView? = null
    var mostViewedRecycler: RecyclerView? = null
    var popularFoodRecycler: RecyclerView? = null
    var courseRecycler: RecyclerView? = null
    var adapter: PopularJobsAdapter? = null
    var cadapter: CategoryAdapter? = null
    var adapter2: PopularFoodAdapter? = null
    var adapter3: PopularCourseAdapter? = null
    var navigationView: NavigationView? = null
    var contentView: LinearLayout? = null
    private lateinit var name: String
    private lateinit var fullNameProfile: TextView
    private lateinit var auth : FirebaseAuth

    var drawerLayout: DrawerLayout? = null;
    var menuIcon: ImageView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_dashboard)

        featuredRecycler = findViewById<RecyclerView>(R.id.featured_recycler)
        menuIcon = findViewById<ImageView>(R.id.menu_icon)
        mostViewedRecycler = findViewById<RecyclerView>(R.id.mostViewedRecycler);
        popularFoodRecycler = findViewById<RecyclerView>(R.id.popular_foodRecycler);
        courseRecycler = findViewById(R.id.courseRecycler);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);

        val username : TextView = findViewById(R.id.full_names)
        val sharedPreferences = applicationContext.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        username.setText("$name")

        navigationDrawer();
        featuredRecycler();
        mostViewedRecycler();
        popularFoodRecycler();
        popularRecycler()
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

        adapter3 = PopularCourseAdapter(featuredCourse);
        courseRecycler?.adapter = adapter3;
    }

    private fun navigationDrawer() {
        navigationView!!.bringToFront()
        navigationView!!.setNavigationItemSelectedListener(this)
        navigationView!!.setCheckedItem(R.id.nav_home)
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

    fun featuredRecycler(){
        featuredRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.HORIZONTAL, false))

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
        featuredJobs.add(PopularJobsAdapterClass("Hairdresser",
            "https://www.simplybusiness.co.uk/static/c140c95b2ac29a2fb643d581e4dc3481/87f10/how-to-become-hairdresser-hero.jpg",
            "We are seeking a skilled and compassionate Hairdresser to join our team and provide hair care services to people",
            "RM 2K+ - 5K / per month",
            "Full Time",
            "Cheras",
            "Google LLC, Cheras, Petaling Jaya",
            "• Knowledge of hair care techniques, products, and trends\n• Ability to communicate effectively with clients and understand their hair care needs and preferences\n• Compassionate and patient demeanor\n• Willingness to work occasional evenings and weekends"
        ))

        adapter = PopularJobsAdapter(featuredJobs)
        featuredRecycler?.adapter = adapter
    }

    fun mostViewedRecycler(){
        mostViewedRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.HORIZONTAL, false))

        val featuredCategory: ArrayList<CategoryAdapterClass> = ArrayList();
        featuredCategory.add(CategoryAdapterClass("Kuih Muih", "cat_1"))
        featuredCategory.add(CategoryAdapterClass("Burger", "burger"))
        featuredCategory.add(CategoryAdapterClass("Donuts", "donut"))
        featuredCategory.add(CategoryAdapterClass("Handcraft", "cat_3"))
        featuredCategory.add(CategoryAdapterClass("Artcraft", "ic_1"))

        cadapter = CategoryAdapter(featuredCategory)
        mostViewedRecycler?.adapter = cadapter
    }

    fun popularFoodRecycler(){
        popularFoodRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.HORIZONTAL, false))

        val featuredFood: ArrayList<PopularFoodAdapterClass> = ArrayList();
        featuredFood.add(PopularFoodAdapterClass("Mak Cik Burger", "https://www.seriouseats.com/thmb/e16lLOoVEix_JZTv7iNyAuWkPn8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__recipes__images__2014__09__20140918-jamie-olivers-comfort-food-insanity-burger-david-loftus-f7d9042bdc2a468fbbd50b10d467dafd.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", 10.0))
        featuredFood.add(PopularFoodAdapterClass("Kedai Ali Nasi Lemak", "https://www.angsarap.net/wp-content/uploads/2014/11/Nasi-Lemak-Wide.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", 20.0))
        featuredFood.add(PopularFoodAdapterClass("Fatimah Donut", "https://static.onecms.io/wp-content/uploads/sites/43/2022/05/26/45921-crispy-and-creamy-doughnuts-ddmfs-638-3x4-1.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", 28.0))
        featuredFood.add(PopularFoodAdapterClass("Handcraft Basket", "https://img.freepik.com/premium-photo/emty-wicker-basket-craftsmanship-from-thailand-beautiful-handcraft_637068-8.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", 28.0))
        featuredFood.add(PopularFoodAdapterClass("That Style Handmade Hat", "https://thumbs.dreamstime.com/b/native-thai-style-handmade-hat-12270244.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", 28.0))
        featuredFood.add(PopularFoodAdapterClass("Handcrafted Bag", "https://cdn.shopify.com/s/files/1/0172/1463/6096/articles/unnamed_a3d7ead5-ceae-4633-b561-8be6b9c9b58b.jpg?v=1593761630", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", 28.0))
        featuredFood.add(PopularFoodAdapterClass("Handmade Bracelet", "https://media.karousell.com/media/photos/products/2022/4/26/handmade_bracelet_03_1651013415_fa7bca49.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", 28.0))

        adapter2 = PopularFoodAdapter(featuredFood);
        popularFoodRecycler?.adapter = adapter2;
    }

    companion object {
        //Variables
        const val END_SCALE = 0.7f
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

    fun callNotifScreen(item: MenuItem) {
        startActivity(Intent(this, notifications_home::class.java))
        finish()
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

    fun callEduPage(view: View) {
        startActivity(Intent(this, education::class.java))
        finish()
    }

    fun callNotifPage(view: View) {
        startActivity(Intent(this, notifications_home::class.java))
        finish()
    }
}