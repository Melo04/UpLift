package com.example.kitahack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitahack.adapter.*
import com.example.kitahack.model.RestaurantModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class marketplace : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var navigationView: NavigationView? = null
    var contentView: LinearLayout? = null
    var drawerLayout: DrawerLayout? = null
    var menuIcon: ImageView? = null;
    var adapter: CategoryAdapter2? = null
    var adapter2: PopularFoodAdapter2? = null
    var mainCategoryRecycler: RecyclerView? = null
    var productsRecycler: RecyclerView? = null
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        menuIcon = findViewById<ImageView>(R.id.menu_icon)
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);
        mainCategoryRecycler = findViewById<RecyclerView>(R.id.recommendedRecycler);
        productsRecycler = findViewById<RecyclerView>(R.id.productsRecycler);

        val username : TextView = findViewById(R.id.textView40)
        val sharedPreferences = applicationContext.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        username.setText("Good Evening \n$name")

        val burger = findViewById<Button>(R.id.burger)
        val nasilemak = findViewById<Button>(R.id.nasilemak)
        val indian = findViewById<Button>(R.id.indian)
        val malay = findViewById<Button>(R.id.malay)
        val vietnam = findViewById<Button>(R.id.vietnam)

        nasilemak.setOnClickListener {
            openSubCategory("Nasi lemak")
        }
        burger.setOnClickListener {
            openSubCategory("Burger")
        }
        indian.setOnClickListener {
            openSubCategory("Indian Cuisine")
        }
        malay.setOnClickListener {
            openSubCategory("Malay Cuisine")
        }
        vietnam.setOnClickListener {
            openSubCategory("Vietnamese")
        }

        navigationDrawer();
        mainCategoryRecycler();
        productsRecycler();
    }

    fun openSubCategory(cat: String) {
        val intent = Intent(this, Restaurant::class.java)
        intent.putExtra("cat", cat)
        startActivity(intent)
    }

    fun mainCategoryRecycler(){
        mainCategoryRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.HORIZONTAL, false))

        val featuredCategory: ArrayList<CategoryAdapterClass2> = ArrayList();
        featuredCategory.add(CategoryAdapterClass2("Kuih Muih", "cat_1"))
        featuredCategory.add(CategoryAdapterClass2("Burger", "burger"))
        featuredCategory.add(CategoryAdapterClass2("Handcraft", "basket"))
        featuredCategory.add(CategoryAdapterClass2("Donuts", "donut"))
        featuredCategory.add(CategoryAdapterClass2("Artcraft", "ic_1"))

        adapter = CategoryAdapter2(featuredCategory)
        mainCategoryRecycler?.adapter = adapter
    }

    fun productsRecycler(){
        productsRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

        val featuredFood: ArrayList<PopularFoodAdapterClass2> = ArrayList();
        featuredFood.add(PopularFoodAdapterClass2("Mak Cik Burger", "https://www.seriouseats.com/thmb/e16lLOoVEix_JZTv7iNyAuWkPn8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__recipes__images__2014__09__20140918-jamie-olivers-comfort-food-insanity-burger-david-loftus-f7d9042bdc2a468fbbd50b10d467dafd.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Burger", 10.0))
        featuredFood.add(PopularFoodAdapterClass2("Kedai Ali Nasi Lemak", "https://www.angsarap.net/wp-content/uploads/2014/11/Nasi-Lemak-Wide.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Nasi lemak", 5.0))
        featuredFood.add(PopularFoodAdapterClass2("Fatimah Donut", "https://static.onecms.io/wp-content/uploads/sites/43/2022/05/26/45921-crispy-and-creamy-doughnuts-ddmfs-638-3x4-1.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Donut", 6.0))
        featuredFood.add(PopularFoodAdapterClass2("Handcraft Basket", "https://img.freepik.com/premium-photo/emty-wicker-basket-craftsmanship-from-thailand-beautiful-handcraft_637068-8.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Handcrafted", 35.0))
        featuredFood.add(PopularFoodAdapterClass2("That Style Handmade Hat", "https://thumbs.dreamstime.com/b/native-thai-style-handmade-hat-12270244.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Handcrafted", 25.0))
        featuredFood.add(PopularFoodAdapterClass2("Ethnic Style Pen Stand", "https://i5.walmartimages.com/asr/c97e4384-d7c7-4d08-9212-6704e79a0c1a.d99d2d412408c0e50368b4d4fce7205c.jpeg?odnHeight=2000&odnWidth=2000&odnBg=ffffff", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Handcrafted", 30.0))
        featuredFood.add(PopularFoodAdapterClass2("Handcrafted Bag", "https://cdn.shopify.com/s/files/1/0172/1463/6096/articles/unnamed_a3d7ead5-ceae-4633-b561-8be6b9c9b58b.jpg?v=1593761630", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Handcrafted", 35.0))
        featuredFood.add(PopularFoodAdapterClass2("Handmade Bracelet", "https://media.karousell.com/media/photos/products/2022/4/26/handmade_bracelet_03_1651013415_fa7bca49.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Handcrafted", 15.0))
        featuredFood.add(PopularFoodAdapterClass2("Handmade mirror", "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71I0I9sbbTL._SL1500_.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Handcrafted", 35.0))
        featuredFood.add(PopularFoodAdapterClass2("Handcrafted Ocean Mug", "https://decobate.com/wp-content/uploads/2018/11/ocean-blye-speckled-mug-handmade-ceramic.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Handcrafted", 35.0))
        featuredFood.add(PopularFoodAdapterClass2("Handmade Leather Bag", "https://cf.shopee.com.my/file/c2a8791566d68ec6e6bb9d5fd069cbc1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Handcrafted", 35.0))
        featuredFood.add(PopularFoodAdapterClass2("Hot Dog Bread", "https://www.theflavorbender.com/wp-content/uploads/2015/07/Hot-dog-buns-4425-700x1049.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Donut", 6.0))
        featuredFood.add(PopularFoodAdapterClass2("Fried Rice", "https://www.seriouseats.com/thmb/tuMCogfAOy2zNdVqE7ydUwuru9Q=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/easy-vegetable-fried-rice-recipe-hero-2-fed2a62b8bce4c51b945d9c24c2edb68.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Donut", 6.0))
        featuredFood.add(PopularFoodAdapterClass2("Sandwiches", "https://lovefoodies.com/wp-content/uploads/2021/04/Classic-Club-SandwichF-e1662546009821-735x735.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "Donut", 6.0))

        adapter2 = PopularFoodAdapter2(featuredFood);
        productsRecycler?.adapter = adapter2;
    }

    private fun navigationDrawer() {
        navigationView!!.bringToFront()
        navigationView!!.setNavigationItemSelectedListener(this)
        navigationView!!.setCheckedItem(R.id.nav_marketplace)
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