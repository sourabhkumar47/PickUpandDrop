package com.example.pickupanddrop.delivery.domain.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


object LocationUtils {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun getLocationFlow(context: Context): Flow<String> = callbackFlow {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            trySend("Location permission not granted").isSuccess
            return@callbackFlow
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    trySend("Latitude: $latitude, Longitude: $longitude").isSuccess
                } else {
                    trySend("Location not available").isSuccess
                }
                close()
            }
            .addOnFailureListener { e ->
                trySend("Error getting location: ${e.message}").isSuccess
                close(e)
            }

        awaitClose()
    }
}