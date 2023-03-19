package com.example.kitahack

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.kitahack.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentLocation: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var latLng: LatLng

        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        latLng = getLatLongFromAddress(this, address.toString())
        mMap.addMarker(MarkerOptions().position(latLng).title(name))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18F))

        // Check for location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            // Get the user's current location
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    // Create a LatLng object for the user's current location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=$address&mode=l&dirflg=d"))
                    intent.putExtra("origin", "${currentLatLng.latitude},${currentLatLng.longitude}")

                    // Check if Google Maps is installed on the device
                    if (intent.resolveActivity(packageManager) != null) {
                        // Start the navigation
                        startActivity(intent)
                    }
                }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    fun getLatLongFromAddress(context: Context, address: String): LatLng {
        val geocoder = Geocoder(context)
        val addresses: List<Address>?
        var latLng: LatLng? = null
        try {
            addresses = geocoder.getFromLocationName(address, 2)
            if (!addresses.isNullOrEmpty()) {
                val location = addresses[0]
                latLng = LatLng(location.latitude, location.longitude)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return latLng!!
    }

}