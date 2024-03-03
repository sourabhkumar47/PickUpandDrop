package com.example.pickupanddrop.delivery.components

import android.location.Location
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

    var isMapLoading by rememberSaveable {
        mutableStateOf(true)
    }
    Box(modifier = modifier) {
        Column {
            if (isMapLoading)
                LinearProgressIndicator(modifier = Modifier.height(10.dp))

            GoogleMap(
                cameraPositionState = cameraPositionState,
                onMapClick = { latLng ->
                    // update the position of the marker as the user clicks on the map
                    markerState.position = latLng
                    // update the coordinates according to the marker position
                    viewModel.updateLatitude(latLng.latitude)
                    viewModel.updateLongitude(latLng.longitude)
                },
                onMapLoaded = {
                    isMapLoading = false
                }
            ) {
                Marker(
                    state = markerState,
                    draggable = true,
                    title = "Location",
                    snippet = "Choose Drop location"
                )
                LaunchedEffect(lat, lng) {
                    scope.launch {
                        viewModel.updateLocationName(getLocationName(context, lat, lng).toString())
                    }
                }
            }
        }
    }
}

@Composable
fun LocationTraceView(modifier: Modifier = Modifier, lineHeight: Dp) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .size(16.dp)
            .background(MaterialTheme.colorScheme.primary.copy(0.8f), CircleShape)) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "pickup location",
                tint = Color.White
            )
        }
        DashedLine(modifier = Modifier.height(lineHeight))
        Column(modifier = Modifier
            .size(16.dp)
            .background(Color.Green.copy(0.8f), CircleShape)) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "pickup location",
                tint = Color.White
            )
        }
    }
}

@Composable
fun DashedLine(modifier: Modifier = Modifier) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(9f, 9f), 0f)
    Canvas(
        modifier
            .width(1.dp)
    ) {

        drawLine(
            color = Color.LightGray,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height),
            pathEffect = pathEffect,
            strokeWidth = 5f
        )
    }
}

@Preview
@Composable
fun DashedLinePreview() {
    DashedLine()
}
