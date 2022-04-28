package com.ripeer.orgatrip.feature_user.presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ripeer.orgatrip.core.util.Screens

@Composable
fun WelcomeScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Déjà inscrit ?",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(Screens.LoggingScreen.route)
        })
        {
            Text(text = "Se connecter")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Nouveau ?",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(Screens.RegisterScreen.route)
        })
        {
            Text(text = "S'inscrire")
        }
    }
}