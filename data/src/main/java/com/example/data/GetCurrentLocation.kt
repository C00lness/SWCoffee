package com.example.data

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Looper
import android.util.Log
import com.example.domain.entities.Point
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

private lateinit var locationClient: FusedLocationProviderClient

@SuppressLint("MissingPermission")
fun getCurrentLocationFun(context: Activity): Any {
    var point = Point("", "")
    locationClient = LocationServices.getFusedLocationProviderClient(context)
    return locationClient.requestLocationUpdates(
        createLocationRequest(),
        { location ->
            Log.d("tempLog", "Func " + location.toString())
            point = Point(location.latitude.toString(), location.longitude.toString())
        },
        Looper.getMainLooper()
    )
}

@SuppressLint("NewApi")
private fun createLocationRequest(): LocationRequest {
    return LocationRequest.Builder(1).build()
}

