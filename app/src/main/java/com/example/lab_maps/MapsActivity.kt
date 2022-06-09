package com.example.lab_maps

import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.lab_maps.API.EndPoints
import com.example.lab_maps.API.MapsOutput
import com.example.lab_maps.API.serviceBuilder

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.lab_maps.databinding.ActivityMapsBinding
import com.google.android.gms.location.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var continenteLat: Double = 0.0
    private var continenteLng: Double = 0.0

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var locationRequest: LocationRequest
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            lastLocation = p0.lastLocation
            val loc = LatLng(lastLocation.latitude, lastLocation.longitude)
            val address = getAddress(lastLocation.latitude, lastLocation.longitude)
            findViewById<TextView>(R.id.txtmorada).setText("Morada: " + address)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15.0f))
            findViewById<TextView>(R.id.txtcoordenadas).setText("Lat: " + loc.latitude + " - Long: " + loc.longitude)
            findViewById<TextView>(R.id.txtdistancia).setText(
                "Distancia: " + calculateDistance(
                    lastLocation.latitude, lastLocation.longitude,
                    continenteLat, continenteLng
                ).toString()
            )
            Log.d("**** SARA", "new location received - " + loc.latitude + " -" + loc.longitude)
        }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        continenteLat = 41.7043
        continenteLng = -8.8148

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        createLocationRequest()
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        val request = serviceBuilder.buildService(EndPoints::class.java)
        val call = request.getUsers()
        var position: LatLng
        mMap = googleMap

        call.enqueue(object : Callback<List<MapsOutput>> {
            override fun onResponse(call: Call<List<MapsOutput>>, response: Response<List<MapsOutput>>) {
                if (response.isSuccessful) {
                    val users = response.body()!!
                    for (user in users) {
                        position =
                            LatLng(user.address.geo.lat.toDouble(), user.address.geo.lng.toDouble())
                        mMap.addMarker(
                            MarkerOptions().position(position)
                                .title(user.address.zipcode + " - " + user.address.city)
                        )
                    }
                }
            }

            override fun onFailure(call: Call<List<MapsOutput>>, t: Throwable) {
                Toast.makeText(this@MapsActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun calculateDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Float {
        val results = FloatArray(1)
        Location.distanceBetween(lat1, lng1, lat2, lng2, results)
        return results[0]

    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(lat, lng, 1)
        return list[0].getAddressLine(0)
    }      

    fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        } else {
            mMap.isMyLocationEnabled = true

            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                run {
                    if (location != null) {
                        lastLocation = location
                        Toast.makeText(
                            this@MapsActivity,
                            lastLocation.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        val currentLatLng = LatLng(location.latitude, location.longitude)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                    }
                }
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val REQUEST_CHECK_SETTINGS = 2
    }
}