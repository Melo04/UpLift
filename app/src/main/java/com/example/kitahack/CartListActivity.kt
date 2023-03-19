package com.example.kitahack

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitahack.Interface.ChangeNumberItemsListener
import com.example.kitahack.adapter.CartListAdapter
import com.example.kitahack.model.ManagementCart
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class CartListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var navigationView: NavigationView? = null
    var contentView: LinearLayout? = null
    var drawerLayout: DrawerLayout? = null;
    var menuIcon: ImageView? = null;
    private lateinit var auth : FirebaseAuth
    private lateinit var recyclerViewList: RecyclerView
    private lateinit var managementCart: ManagementCart
    private lateinit var totalFeeTxt: TextView
    private lateinit var taxTxt: TextView
    private lateinit var deliveryTxt: TextView
    private lateinit var totalTxt: TextView
    private lateinit var emptyTxt: TextView
    private var tax: Double = 0.0
    private lateinit var scrollView: ScrollView
    var adapter: CartListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)

        managementCart = ManagementCart(this)
        menuIcon = findViewById(R.id.menu_icon)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        contentView = findViewById(R.id.content)

        initView()
        initList()
        CalculateCart()
        navigationDrawer()
    }

    private fun initView(){
        recyclerViewList = findViewById(R.id.cartView)
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        taxTxt = findViewById(R.id.taxTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        totalTxt = findViewById(R.id.total)
        emptyTxt = findViewById(R.id.emptyCart)
        scrollView = findViewById(R.id.scrollView)
    }

    private fun navigationDrawer() {
        navigationView!!.bringToFront()
        navigationView!!.setNavigationItemSelectedListener(this)
        navigationView!!.setCheckedItem(R.id.nav_cart)
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

    private fun initList(){
        recyclerViewList.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

        val changeNumberItemsListener = object : ChangeNumberItemsListener {
            override fun changed() {
                CalculateCart()
            }
        }

        adapter = CartListAdapter(managementCart.listCart, this, changeNumberItemsListener)
        recyclerViewList.adapter = adapter

        if(managementCart.listCart.isEmpty()){
            emptyTxt.visibility = View.VISIBLE
            scrollView.visibility = View.GONE
        }else{
            emptyTxt.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun CalculateCart(){
        val percentTax : Double = 0.02;
        val delivery : Double = 10.0;

        tax = (Math.round((managementCart.getTotalFee()*percentTax) * 100)/100).toDouble();
        val total : Long = Math.round((managementCart.getTotalFee() + tax+delivery)*100)/100
        val itemTotal : Long = Math.round(managementCart.getTotalFee()*100)/100

        totalFeeTxt.text = "RM" + itemTotal
        taxTxt.text = "RM" + tax
        deliveryTxt.text = "RM" + delivery
        totalTxt.text = "RM" + total
    }

    fun callDashboardPage(view: View) {
        startActivity(Intent(this, Dashboard::class.java))
        finish()
    }

    fun callProfilePage(view: View) {
        startActivity(Intent(this, UserProfile::class.java))
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

    fun callCartPage(view: View) {
        startActivity(Intent(this, CartListActivity::class.java))
        finish()
    }
}