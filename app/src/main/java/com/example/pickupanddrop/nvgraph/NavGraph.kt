package com.example.pickupanddrop.nvgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.pickupanddrop.app_navigator.AppNavigator

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.Navigation.route,
            startDestination = Route.NavigatorScreen.route
        ) {
            composable(route = Route.NavigatorScreen.route) {
                AppNavigator()
            }
        }
    }
}