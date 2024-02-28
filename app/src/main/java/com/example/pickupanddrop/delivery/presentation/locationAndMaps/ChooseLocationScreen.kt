package com.example.pickupanddrop.delivery.presentation.locationAndMaps

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun ChooseLocationScreen(modifier: Modifier = Modifier) {
    var lat by remember {
        mutableDoubleStateOf(22.4)
    }
    var lng by remember {
        mutableDoubleStateOf(79.4)
    }
    // coordinates where we need to set the marker
    val coordinates = LatLng(lat, lng)
    // position where to focus after the map is loaded
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coordinates, 10f)
    }
    val markerState = rememberMarkerState(position = coordinates)

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            // update the position of the marker as the user clicks on the map
            markerState.position = latLng
            lat = latLng.latitude
            lng = latLng.longitude
        }
    ) {
        Marker(
            state = markerState,
            draggable = true,
            title = "Location",
            snippet = "Pickup drop location"
        )
    }
}