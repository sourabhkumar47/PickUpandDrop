package com.example.pickupanddrop.delivery.domain.location

import android.location.Location
import android.util.Log
import com.google.android.gms.maps.model.LatLng

fun calculateDistance(latLng1: LatLng, latLng2: LatLng){
    val startPoint= Location("locationA")
    startPoint.latitude = latLng1.latitude
    startPoint.longitude = latLng1.longitude

    val endPoint= Location("locationA")
    endPoint.latitude = latLng2.latitude
    endPoint.longitude = latLng2.longitude

    val distance = startPoint.distanceTo(endPoint).toDouble()

    Log.d("distance", "Distance: $distance m")
}