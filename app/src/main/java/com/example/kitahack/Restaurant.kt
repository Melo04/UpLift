package com.example.kitahack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitahack.adapter.RestaurantAdapter
import com.example.kitahack.model.RestaurantModel

class Restaurant : AppCompatActivity() {
    val list = ArrayList<RestaurantModel>()
    var adapter: RestaurantAdapter? = null
    var productsRecycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val cat = intent.getStringExtra("cat")
        val title = findViewById<TextView>(R.id.title)
        productsRecycler = findViewById(R.id.productsRecycler);
        title.setText(cat + "\nRestaurants List")

        if (cat.equals("Nasi lemak")) {
            productsRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

            val featuredFood: ArrayList<RestaurantModel> = ArrayList()
            featuredFood.add(RestaurantModel("Nasi Lemak Panas Restoran Warisan Sambal Opah", "2-1, Jalan USJ 9/5p, Subang Business Centre, 47620 Subang Jaya, Selangor", "https://lh5.googleusercontent.com/p/AF1QipNCkN5yPY-olZvyyg3Kpvn0VWSiDccvi7184TTS=s901-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","nasi lemak", 20.0))
            featuredFood.add(RestaurantModel("Nasi Lemak MZ Baldi", "Persiaran Saujana Puchong, Taman Saujana Puchong, 47100 Puchong, Selangor", "https://lh3.googleusercontent.com/p/AF1QipOCGKwcBb_L5nHkFpX00cx7Z-tmLCJeOKFBMzPM=s1360-w1360-h1020", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","nasi lemak", 20.0))
            featuredFood.add(RestaurantModel("Brilliant Nasi Lemak House", "2, Jalan Puteri 1/2, Bandar Puteri, 47100 Kuala Lumpur, Selangor", "https://lh5.googleusercontent.com/p/AF1QipOSP0H-84DT5aebVPGtfyVo5GiNpTJs3QkloRR2=w157-h157-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","nasi lemak", 20.0))
            featuredFood.add(RestaurantModel("Nasi Lemak Embun", "lot 3, Jalan Kampung Kuala Sungai Baru, Kampung Kuala Sungai Baru, 47150 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipPFfQJ93l_WcMjs7ogDMdWp1b5GwDZ4OlMjWueB=w357-h240-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","nasi lemak", 20.0))
            featuredFood.add(RestaurantModel("Zaita Nasi Lemak Berlauk", "Tesco Extra Puchong, Bandar Bukit, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipN7m23qgYQlVVvsB-d_mNbE83RheT1vZMaNJRUP=w357-h240-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","nasi lemak", 20.0))
            featuredFood.add(RestaurantModel("Nasi Lemak Resepi Umi 52", "Lorong Dagang Mas 2, Taman Mas, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipMTaZhyfwDR3hO5EMQjbykEx8L4ELVfKmc3hiwB=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","nasi lemak", 20.0))
            featuredFood.add(RestaurantModel("Nasi Lemak Colombia", "berhadapan balai polis bandar puteri puchong bandar puteri pucong, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipMF_tn5Dl8Idzp4-ltgvB9Cl9wxeb7SX21cHLfK=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","nasi lemak", 20.0))
            adapter = RestaurantAdapter(featuredFood)
            productsRecycler?.adapter = adapter;
        }
        else if (cat.equals("Burger")) {
            productsRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

            val featuredFood: ArrayList<RestaurantModel> = ArrayList()
            featuredFood.add(RestaurantModel("Farid Burger", "Sebelah kios, Jalan Perdana 4/4, Taman Puchong Perdana, 47150 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipMysovwpeM1pXm-B1YD_aEOY7v2w_i7E9RNzv8t=w357-h240-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","burger", 20.0))
            featuredFood.add(RestaurantModel("Burger Berdepot", "Lot 182, Jalan Bistari, Kampung Seri Aman, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipNeqj1rX81llLLHDPVz5YWhthVNHylJ04KEEjqZ=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","burger", 20.0))
            featuredFood.add(RestaurantModel("Burgerlicious", "Burgerlicious, Persiaran Puchong Prima, Taman Puchong Permai, 47500 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipPkKQxgvwZicisMZvnE8KKPQ7DdYrI2VLECItaf=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","burger", 20.0))
            featuredFood.add(RestaurantModel("Tengku Burger", "219, Jalan Bistari, Kampung Sri Aman Bistari, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipOakL3-eTxmCwPGdhvVx6kYgFZpGIYM1-VgXfSG=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","burger", 20.0))
            featuredFood.add(RestaurantModel("Burger Buddies", "Jalan Indah 3, Taman Puchong Indah, 47150 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipO9b9BR_x5PmF6pxRCUHXtnsk4N0PvuSRyHHaTJ=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","burger", 20.0))
            featuredFood.add(RestaurantModel("Burger Cowboy Corner", "Taman Puchong Utama, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipO3jM7rI-iWIXrw39NKD5AnEDeEV9n53rVrtmve=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","burger", 20.0))

            adapter = RestaurantAdapter(featuredFood)
            productsRecycler?.adapter = adapter;
        }
        else if (cat.equals("Indian Cuisine")) {
            productsRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

            val featuredFood: ArrayList<RestaurantModel> = ArrayList()
            featuredFood.add(RestaurantModel("Spice Up Indian Cuisine", "No.29 (Ground Floor, Jalan Indah 3/13, Taman Puchong Indah, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipMcS12EY-e-QrgG5IFsdY2h2AMybKvcrJtNWEL7=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "indian cuisine", 20.0))
            featuredFood.add(RestaurantModel("Restoran mp spice", "13, Jalan Intan 2/7, Taman Puchong Intan, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipO4W3tzGczCYbpJ1sX3CpKV34lha9yyokSAPvtH=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "indian cuisine", 20.0))
            featuredFood.add(RestaurantModel("Rathaa Curry House", "22, Jalan Puteri 1/5, Bandar Puteri, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipN6-rRV_MZ4b-dNccSv87NTzKSMLv2ILL5loeI-=w179-h120-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "indian cuisine", 20.0))
            featuredFood.add(RestaurantModel("Sre Seetharam Curry House (H.q)", "No.24, Jalan Dagang SB 4/2, Taman Sungai Besi Indah, 43300 Seri Kembangan, Selangor", "https://lh5.googleusercontent.com/p/AF1QipMZybi5rxhDdwH0boCC5p9dTRzGBZ6_R6BZnlDO=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "indian cuisine", 20.0))
            featuredFood.add(RestaurantModel("Kanna Curry House", "29, Jalan 17/45, Seksyen 17, 46400 Petaling Jaya, Selangor", "https://lh5.googleusercontent.com/p/AF1QipNE1ED8mmEBNbuybQsPQNnkFLOKZAqOcLA43NbX=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "indian cuisine", 20.0))
            featuredFood.add(RestaurantModel("Restoran Cellam (Indian Cuisine)", "No. 80 Lorong Maarof Taman Bangsar Park, Bangsar, 59000 Kuala Lumpur", "https://lh5.googleusercontent.com/p/AF1QipPHN7lBfinkO0a3nXPjrXt68QaB1e9RiZ_kWRPD=w717-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "indian cuisine", 20.0))

            adapter = RestaurantAdapter(featuredFood)
            productsRecycler?.adapter = adapter;
        }
        else if (cat.equals("Malay Cuisine")) {
            productsRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

            val featuredFood: ArrayList<RestaurantModel> = ArrayList()
            featuredFood.add(RestaurantModel("Restoran Aisyah Tan", "32, Jalan Puteri 5/1, Bandar Puteri, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipNZHblVvOzNcUXMu5l58KsmMf2nwAu8aL6udQFS=w130-h87-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "malay cuisine", 20.0))
            featuredFood.add(RestaurantModel("Nasi Ayam Malaya Puchong Prima", "Taman Puchong Prima, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipO2Hv3CsOhMUzQ4BaTLy6ihGkxsxfAXRZle9fSK=w130-h87-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "malay cuisine", 20.0))
            featuredFood.add(RestaurantModel("Sai Gon Viet 2 Cafe", "S-00-05, Sutramas, Persiaran Puchong Jaya Selatan, Bandar Puchong Jaya, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipOiAINKr6AWN90Mm0-ApifM86LKVDNWFzuLu1P9=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "malay cuisine", 20.0))
            featuredFood.add(RestaurantModel("Dai Viet Vietnamese Restaurant", "No 8G and 9 G, Jalan Kenari 10, Bandar Puchong Jaya, 47100, Selangor", "https://lh5.googleusercontent.com/p/AF1QipNWxhTNEc4UpGvhIg0BI3ucxEs0_iKV_DFA4mvT=w86-h87-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "malay cuisine", 20.0))
            featuredFood.add(RestaurantModel("MyViets Kelana Jaya", "Ss 6, 47301 Petaling Jaya, Selangor", "https://lh5.googleusercontent.com/p/AF1QipPcTLJT0-bSLgZOrUGSfV_nsFaK0DKTpdfFtljn=w260-h175-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "malay cuisine", 20.0))

            adapter = RestaurantAdapter(featuredFood)
            productsRecycler?.adapter = adapter;
        }
        else if (cat.equals("Vietnamese")) {
            productsRecycler?.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

            val featuredFood: ArrayList<RestaurantModel> = ArrayList()
            featuredFood.add(RestaurantModel("Bun Viet Restaurant", "16, Jalan Puteri 5/5, Bandar Puteri, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipPV1ZMFRv89yy8T2Y1zO1wCIN0LL4PqAv9QXdM=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "vietnamese", 20.0))
            featuredFood.add(RestaurantModel("MIMI NGUYEN CAFE", "L-01-01 Jalan PPK 1 Pusat Perniagaan Kinrara, Taman Kinrara, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipN4B_kKLGzsQePVPP50XH0kopF49Fb8KjJR7EC6=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "vietnamese", 20.0))
            featuredFood.add(RestaurantModel("Sai Gon Viet 2 Cafe", "S-00-05, Sutramas, Persiaran Puchong Jaya Selatan, Bandar Puchong Jaya, 47100 Puchong, Selangor", "https://lh5.googleusercontent.com/p/AF1QipOiAINKr6AWN90Mm0-ApifM86LKVDNWFzuLu1P9=w358-h241-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "vietnamese", 20.0))
            featuredFood.add(RestaurantModel("Dai Viet Vietnamese Restaurant", "No 8G and 9 G, Jalan Kenari 10, Bandar Puchong Jaya, 47100, Selangor", "https://lh5.googleusercontent.com/p/AF1QipNWxhTNEc4UpGvhIg0BI3ucxEs0_iKV_DFA4mvT=w86-h87-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "vietnamese", 20.0))
            featuredFood.add(RestaurantModel("MyViets Kelana Jaya", "Ss 6, 47301 Petaling Jaya, Selangor", "https://lh5.googleusercontent.com/p/AF1QipPcTLJT0-bSLgZOrUGSfV_nsFaK0DKTpdfFtljn=w260-h175-n-k-no", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", "vietnamese", 20.0))

            adapter = RestaurantAdapter(featuredFood)
            productsRecycler?.adapter = adapter;
        }
    }

    fun callMarketPage(view: View) {
        startActivity(Intent(this, marketplace::class.java))
        finish()
    }
}