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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pickupanddrop.delivery.components.DropLocationCard
import com.example.pickupanddrop.delivery.components.PickUpLocationCard

@Composable
fun DeliveryDetailsScreen() {
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

            })
        }
    }
}