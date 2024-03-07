package com.example.pickupanddrop.delivery.domain

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pickupanddrop.delivery.components.ChooseLocationFromMaps
import com.example.pickupanddrop.delivery.presentation.deliveryDetails.DeliveryDetailsScreen
import com.example.pickupanddrop.delivery.presentation.locationAndMaps.ChooseLocationScreen
import com.example.pickupanddrop.presentation.checkout.Checkout
import com.example.pickupanddrop.presentation.checkout.CheckoutUI
import com.example.pickupanddrop.presentation.checkout.Discount

@Composable
fun NavigationForMaps(
    navHostController: NavHostController,
    viewModel: MapsViewModel
) {
    NavHost(navController = navHostController, startDestination = "delivery_details"){
        composable("delivery_details"){
            DeliveryDetailsScreen(viewModel = viewModel, navController = navHostController)
        }
        composable("choose_location"){
            ChooseLocationScreen(viewModel = viewModel, navController = navHostController)
        }

        composable("checkout") { // Add this composable
            CheckoutUI(checkout  = Checkout(), discount = Discount())
        }
    }
}