package com.example.pickupanddrop.delivery.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.pickupanddrop.delivery.domain.MapsViewModel
import com.example.pickupanddrop.delivery.presentation.locationAndMaps.getLocationName
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch

@Composable
fun ChooseLocationFromMaps(
    modifier: Modifier = Modifier,
    viewModel: MapsViewModel
) {
    val context = LocalContext.current

    val lat by viewModel.lat.collectAsState()
    val lng by viewModel.lng.collectAsState()

    // coordinates where we need to set the marker
    val coordinates = LatLng(lat, lng)

    // position where to focus after the map is loaded
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coordinates, 10f)
    }
    val markerState = rememberMarkerState(position = coordinates)

    val scope = rememberCoroutineScope()

    // name of clicked location
    var locationName by rememberSaveable {
        mutableStateOf("Pickup drop location")
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            // update the position of the marker as the user clicks on the map
            markerState.position = latLng
            // update the coordinates according to the marker position
            viewModel.updateLatitude(latLng.latitude)
            viewModel.updateLongitude(latLng.longitude)
        }
    ) {
        Marker(
            state = markerState,
            draggable = true,
            title = "Location",
            snippet = "Choose Drop location"
        )
        LaunchedEffect(lat, lng){
            scope.launch {
                viewModel.updateLocationName(getLocationName(context, lat, lng).toString())
                Log.d("location_name", locationName)
            }
        }
    }
}