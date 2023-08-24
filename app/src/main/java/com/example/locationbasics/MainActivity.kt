package com.example.locationbasics

import android.Manifest

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
//LocationBasics - Location feature on Apps
class MainActivity : AppCompatActivity(),LocationListener {
    private lateinit var  locationManager:LocationManager
    private lateinit var tvOuput :TextView
    private val locationPermissionCode =2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button :Button =findViewById(R.id.btnLocations)
        button.setOnClickListener(){
            getLocation()
        }
    }

    private fun getLocation() {
        locationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if((ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                    !=PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            5000,5f,this)
    }

    override fun onLocationChanged(location: Location) {
        tvOuput = findViewById(R.id.lblOutput)
        tvOuput.text= "latitude "+location.latitude+" ,Longitude"+location.longitude
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==locationPermissionCode){
            if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted ",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Permission Granted ",Toast.LENGTH_SHORT).show()
            }
        }
    }
}