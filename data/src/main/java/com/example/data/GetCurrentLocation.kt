package com.example.data

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import kotlin.time.times

@SuppressLint("MissingPermission")
//Стырил со stackoverflow.com/questions/45958226/get-location-android-kotlin

fun getLastLocation(context: Context): Location? {
    val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val providers: List<String> = locationManager.getProviders(true)
    var location: Location? = null
    for (i in providers.size - 1 downTo 0) {
        location = locationManager.getLastKnownLocation(providers[i])
        if (location != null)
            break
    }
    return location
}

fun getDistanceFromLatLonInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Int {
    var R = 6371; // Radius of the earth in km
    var dLat = deg2rad(lat2-lat1);  // deg2rad below
    var dLon = deg2rad(lon2-lon1);
    var a =
        Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                Math.sin(dLon/2) * Math.sin(dLon/2)
    ;
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    var d = R * c; // Distance in km
    return d.toInt()
}

fun deg2rad(deg: Double): Double {
    return deg * (Math.PI/180)
}



