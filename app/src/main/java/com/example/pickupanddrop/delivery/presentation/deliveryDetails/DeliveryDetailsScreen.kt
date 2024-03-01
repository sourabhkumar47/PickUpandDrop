package com.example.pickupanddrop.delivery.presentation.deliveryDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pickupanddrop.delivery.Data.LocationData
import com.example.pickupanddrop.delivery.components.DropLocationCard
import com.example.pickupanddrop.delivery.components.LocationDetailsCard
import com.example.pickupanddrop.delivery.domain.MapsViewModel
import com.example.pickupanddrop.common.AdditionalDetails
import com.example.pickupanddrop.common.PickUpLocationCard
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.pickupanddrop.delivery.components.LocationTraceView
import androidx.compose.ui.tooling.preview.Preview

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
            Row {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    LocationTraceView(lineHeight = 162.dp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {

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



            Spacer(modifier = Modifier.height(32.dp))

            AdditionalDetails(
                lengthState = remember { mutableStateOf("") },
                widthState = remember { mutableStateOf("") },
                heightState = remember { mutableStateOf("") }
            )

            Button(
                onClick = { navController.navigate("checkout") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(top = 24.dp)
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeliveryDetailsScreenPreview() {
    DeliveryDetailsScreen(
        viewModel = MapsViewModel(),
        navController = NavController(LocalContext.current)
    )
}