package com.kiran.retrofitstarterbatch26.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gpstracker.R
import com.example.gpstracker.databinding.ActivityMapsBinding
import com.example.gpstracker.model.LatitudeLongitude

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


//import com.kiran.retrofitstarterbatch26.ui.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

    private var lstLatitudeLongitude = ArrayList<LatitudeLongitude>()

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        lstLatitudeLongitude.add(LatitudeLongitude(27.663250110646164, 85.30275461526857, "Hospital"))
        lstLatitudeLongitude.add(LatitudeLongitude(27.649716959176065, 85.29935932408274, "Agape"))

        for (location in lstLatitudeLongitude){
            mMap.addMarker(
                MarkerOptions().position(LatLng(location.latitude, location.longitude))
                    .title(location.markerName)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(27.649716959176065, 85.29935932408274), 20F)
            , 4000, null)

        mMap.uiSettings.isZoomControlsEnabled = true

    }
}