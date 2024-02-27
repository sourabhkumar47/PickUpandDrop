package com.example.pickupanddrop.app_navigator

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pickupanddrop.R
import com.example.pickupanddrop.app_navigator.components.BottomNavigation
import com.example.pickupanddrop.app_navigator.components.BottomNavigationItem
import com.example.pickupanddrop.nvgraph.Route
import com.example.pickupanddrop.presentation.HomeScreen
import com.example.pickupanddrop.presentation.PastScreen
import com.example.pickupanddrop.presentation.UpcomingScreen

@Composable
fun AppNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.baseline_home_filled_24, text = "Home"),
            BottomNavigationItem(icon = R.drawable.baseline_auto_mode_24, text = "Upcoming"),
            BottomNavigationItem(icon = R.drawable.baseline_history_24, text = "History"),
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backstackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.UpcomingScreen.route -> 1
        Route.PastScreen.route -> 2
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClicked = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.UpcomingScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.PastScreen.route
                        )
                    }

                }
            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {

                HomeScreen(
                    navigateToUpcoming = {
                        navigateToTab(
                            navController = navController,
                            route = Route.UpcomingScreen.route
                        )
                    }
                )
            }
            composable(route = Route.UpcomingScreen.route) {
                UpcomingScreen(
                    navigateToPast = {
                        navigateToTab(
                            navController = navController,
                            route = Route.PastScreen.route
                        )
                    }
                )
            }
            composable(route = Route.PastScreen.route) {
                PastScreen()
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

//on back click it move to bak screen unless of close app
private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}