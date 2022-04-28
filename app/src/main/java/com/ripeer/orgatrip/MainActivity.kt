package com.ripeer.orgatrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ripeer.orgatrip.feature_trip.domain.repository.TripApiKtorClient
import com.ripeer.orgatrip.feature_trip.presentation.trip.TripViewModel
import com.ripeer.orgatrip.feature_trip.presentation.trip.TripsScreen
import com.ripeer.orgatrip.core.util.Screens
import com.ripeer.orgatrip.feature_trip.presentation.HomeScreen
import com.ripeer.orgatrip.feature_user.presentation.login.LoginScreen
import com.ripeer.orgatrip.feature_user.presentation.registration.RegistrationScreen
import com.ripeer.orgatrip.feature_user.presentation.welcome.WelcomeScreen
import com.ripeer.orgatrip.ui.theme.OrgaTripTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            OrgaTripTheme {

                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.WelcomeScreen.route
                    ){
                        composable(route = Screens.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screens.TripScreen.route) {
                            TripsScreen(navController = navController)
                        }
                        composable(route = Screens.LoggingScreen.route) {
                            LoginScreen(navController = navController)
                        }
                        composable(route = Screens.RegisterScreen.route) {
                            RegistrationScreen(navController = navController)
                        }
                        composable(route = Screens.WelcomeScreen.route) {
                            WelcomeScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


