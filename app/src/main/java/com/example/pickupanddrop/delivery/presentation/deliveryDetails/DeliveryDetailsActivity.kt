package com.example.pickupanddrop.delivery.presentation.deliveryDetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.pickupanddrop.delivery.domain.MapsViewModel
import com.example.pickupanddrop.delivery.domain.NavigationForMaps
import com.example.pickupanddrop.delivery.presentation.deliveryDetails.DeliveryDetailsScreen
import com.example.pickupanddrop.ui.theme.PickUpandDropTheme

class DeliveryDetailsActivity : ComponentActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[MapsViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickUpandDropTheme {

                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                NavigationForMaps(navHostController = navController, viewModel = viewModel)
            }
        }
    }
}