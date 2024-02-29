package com.example.pickupanddrop.delivery.presentation.deliveryDetails

import android.content.Intent
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pickupanddrop.common.AdditionalDetails
import com.example.pickupanddrop.common.DropLocationCard
import com.example.pickupanddrop.common.PickUpLocationCard
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.pickupanddrop.delivery.presentation.locationAndMaps.ChooseDropLocationActivity

@Composable
fun DeliveryDetailsScreen() {

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

            PickUpLocationCard(
                franchiseName = "Petuk Ji G.Noida Franchise",
                franchiseLocation = "IT waala digital success D, 296 Greater Noida Wandra",
                ownerName = "Vinay",
                ownerPhoneNo = "7970783256"
            )

            Spacer(modifier = Modifier.height(12.dp))

            DropLocationCard(modifier = Modifier.fillMaxWidth().clickable {
                context.startActivity(Intent(context, ChooseDropLocationActivity::class.java))

            })

            Spacer(modifier = Modifier.height(16.dp))

            AdditionalDetails(
                lengthState = remember { mutableStateOf("") },
                widthState = remember { mutableStateOf("") },
                heightState = remember { mutableStateOf("") }
            )
        }
    }
}