package com.example.pickupanddrop.nvgraph

sealed class Route(
    val route:String
){
    object HomeScreen:Route(route = "homeScreen")
    object UpcomingScreen:Route(route = "upcomingScreen")
    object PastScreen:Route(route = "pastScreen")
    object AppStartNavigation:Route(route = "appStartNavigation")
    object Navigation:Route(route = "Navigation")
    object NavigatorScreen:Route(route = "NavigatorScreen")
}
