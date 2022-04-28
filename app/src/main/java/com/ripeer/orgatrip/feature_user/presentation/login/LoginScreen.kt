package com.ripeer.orgatrip.feature_user.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ripeer.orgatrip.core.util.Screens
import com.ripeer.orgatrip.feature_user.presentation.ValidationEvent

@Composable
fun LoginScreen (
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    navController.navigate(Screens.HomeScreen.route)



                    /*auth.createUserWithEmailAndPassword("bob@t.fr", "bobbob5").addOnSuccessListener {
                        Toast.makeText(context, "regsiter successful", Toast.LENGTH_SHORT).show()
                    }.addOnCanceledListener {
                        Toast.makeText(context, "regsiter canceled", Toast.LENGTH_SHORT).show()
                    }.addOnCompleteListener {
                        println(it.exception)
                    }*/
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.EmailChanged(it))
            },
            isError = state.emailError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
            },
            isError = state.passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.onEvent(LoginFormEvent.Submit)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Submit")
        }
    }

}