package com.example.pickupanddrop.delivery.presentation.deliveryDetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pickupanddrop.delivery.presentation.deliveryDetails.DeliveryDetailsScreen
import com.example.pickupanddrop.ui.theme.PickUpandDropTheme

class DeliveryDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickUpandDropTheme {
                // A surface container using the 'background' color from the theme
                DeliveryDetailsScreen()
            }
        }
    }
}