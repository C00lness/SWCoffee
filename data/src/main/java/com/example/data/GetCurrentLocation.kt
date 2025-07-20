package com.example.data

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private lateinit var locationClient: FusedLocationProviderClient

@SuppressLint("MissingPermission")
fun getCurrentLocationFun(context: Activity) = flow {
    //var point = Point("", "")
    locationClient = LocationServices.getFusedLocationProviderClient(context)
    locationClient.requestLocationUpdates(
        createLocationRequest(),
        { location ->
            //Log.d("tempLog", "Func " + location.toString())
            //point = Point(location.latitude.toString(), location.longitude.toString())
            CoroutineScope(Dispatchers.Default).launch {
                Log.d("tempLog", "Func " + location.toString())
                emit(location.latitude.toString()) }
        },
        Looper.getMainLooper()
    )
}

@SuppressLint("NewApi")
private fun createLocationRequest(): LocationRequest {
    return LocationRequest.Builder(1000000).build()
}

