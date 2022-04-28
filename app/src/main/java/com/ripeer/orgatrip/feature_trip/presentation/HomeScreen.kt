package com.ripeer.orgatrip.feature_trip.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(navController: NavController) {
    Text("Home ${Firebase.auth.currentUser?.uid.toString()}")
}