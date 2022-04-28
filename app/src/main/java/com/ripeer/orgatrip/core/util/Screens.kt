package com.ripeer.orgatrip.core.util

sealed class Screens(val route: String) {
    object WelcomeScreen: Screens("welcome_screen")
    object TripScreen: Screens("trips_screen")
    object LoggingScreen: Screens("logging_screen")
    object RegisterScreen: Screens("register_screen")
    object HomeScreen: Screens("home_screen")
}
