package com.example.orgatrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.orgatrip.ui.theme.OrgaTripTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrgaTripTheme {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val trip = viewModel.state.value.trip
                    val isLoading = viewModel.state.value.isLoading
                    trip.let {
                        Text(text = it?.id ?: "erreur")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if (isLoading){
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

