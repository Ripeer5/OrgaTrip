package com.example.orgatrip.feature_trip.presentation.util

sealed class Screen(val route: String) {
    object TripScreen: Screen("trips_screen")
    object LoggingScreen: Screen("logging_screen")
    object RegisterScreen: Screen("register_screen")
}
