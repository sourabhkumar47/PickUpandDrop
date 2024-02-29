package com.example.pickupanddrop.delivery.presentation.deliveryDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pickupanddrop.delivery.Data.LocationData
import com.example.pickupanddrop.delivery.components.DropLocationCard
import com.example.pickupanddrop.delivery.components.LocationDetailsCard
import com.example.pickupanddrop.delivery.domain.MapsViewModel

@Composable
fun DeliveryDetailsScreen(
    viewModel: MapsViewModel,
    navController: NavController
) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Delivery Details",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
            )
            Spacer(modifier = Modifier.height(18.dp))

            LocationDetailsCard(
                locationData = LocationData(
                    locationType = "PICKUP LOCATION",
                    franchiseName = "Petuk Ji G.Noida Franchise,",
                    franchiseLocation = "IT waala digital success D, 296 Greater Noida Wandra",
                    ownerName = "Vinay",
                    ownerPhoneNo = "7970783256",
                    otpFor = "pickup"
                )
            ) {
                navController.navigate("choose_location")
            }

            Spacer(modifier = Modifier.height(12.dp))

            val isDropLocationAdded by viewModel.isDropLocationAdded.collectAsState()

            val locationData by viewModel.dropLocationData.collectAsState()

            if (isDropLocationAdded)
                LocationDetailsCard(locationData = locationData) {
                    navController.navigate("choose_location")
                }

            if (!isDropLocationAdded)
                DropLocationCard(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("choose_location")
                    })
        }
    }
}