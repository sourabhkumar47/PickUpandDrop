package com.example.pickupanddrop.delivery.presentation.locationAndMaps

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pickupanddrop.delivery.components.ChooseLocationFromMaps
import com.example.pickupanddrop.delivery.domain.MapsViewModel
import kotlinx.coroutines.Dispatchers
import java.io.IOException

@Composable
fun ChooseLocationScreen(
    modifier: Modifier = Modifier,
    viewModel: MapsViewModel,
    navController: NavController
) {
    val locationName by viewModel.dropLocationName.collectAsState()
    val lat by viewModel.lat.collectAsState()
    val lng by viewModel.lng.collectAsState()
    Column(modifier = modifier) {
        // set maps
        ChooseLocationFromMaps(
            modifier = Modifier.weight(1f),
            viewModel = viewModel
        )
        // name of the chosen location
        Column(
            modifier = Modifier
                .weight(0.45f)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // latitude, longitude and drop location name
            Text(
                modifier = Modifier.padding(8.dp),
                text = "$lat, $lng, $locationName",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            val locationData by viewModel.dropLocationData.collectAsState()
            var ownerName by rememberSaveable {
                mutableStateOf(locationData.ownerName)
            }
            var ownerPhone by rememberSaveable {
                mutableStateOf(locationData.ownerPhoneNo)
            }
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(0.5f),
                    value = ownerName,
                    onValueChange = { ownerName = it },
                    label = { Text(text = "Owner Name") },
                    singleLine = true
                )

                OutlinedTextField(
                    modifier = Modifier.weight(0.5f),
                    value = ownerPhone,
                    onValueChange = { ownerPhone = it },
                    label = { Text(text = "Phone No") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp), horizontalArrangement = Arrangement.End
            ) {
                // don't save details and go back
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(text = "Cancel")
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Save details
                Button(
                    onClick = {
                        viewModel.updateDropLocationStatus(true)
                        viewModel.updateDropLocationData(name = ownerName, phoneNo = ownerPhone)
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Save")
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

suspend fun getLocationName(context: Context, lat: Double, lng: Double): String? {
    return with(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context)
            val addresses: List<Address>? = geocoder.getFromLocation(lat, lng, 1)
            if (!addresses.isNullOrEmpty()) {
                val address: Address = addresses[0]
                val stringBuilder = StringBuilder()
                for (i in 0..address.maxAddressLineIndex) {
                    stringBuilder.append(address.getAddressLine(i)).append("\n")
                }
                stringBuilder.toString()
            } else {
                null
            }
        } catch (e: IOException) {
            Log.e("Geocoder", "Error getting location name: ${e.message}")
            null
        }
    }
}