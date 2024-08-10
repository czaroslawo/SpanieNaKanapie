package com.example.spanienakanapie.authorization

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shared.data.models.LoginParams
import com.example.spanienakanapie.navigation.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spanienakanapie.viewmodels.Event
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()) {

    val state by viewModel.state.collectAsState()

    var emailValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val snackBarsState = remember {
        SnackbarHostState()
    }
    val scrollState = rememberScrollState()



    LaunchedEffect(state.event) {
        Log.d("Event", "LaunchedEffewct is triggered")
        state.event?.consume {
            Log.d("Event", state.event.toString())
            if (state.event is Event.NavigateEvent) {
                Log.d("Direct Check", "NavigateEvent detected: ${(state.event as Event.NavigateEvent).route}")
                navController.navigate((state.event as Event.NavigateEvent).route)
            } else if (state.event is Event.SnackbarEvent) {
                Log.d("Direct Check", "SnackbarEvent detected: ${(state.event as Event.SnackbarEvent).message}")
                launch {
                    snackBarsState.showSnackbar((state.event as Event.SnackbarEvent).message)
                }
            }
        }
    }


    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(hostState = snackBarsState) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .consumeWindowInsets(it)
                .systemBarsPadding()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .imePadding()
                    .height(400.dp)
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(16.dp)
                        .imePadding(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        //verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 16.dp,
                                bottom = 16.dp,
                                start = 8.dp,
                                end = 16.dp
                            )


                    ) {

                        Text(
                            text = "Zaloguj się",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(
                                    top = 16.dp,
                                    bottom = 32.dp
                                )
                        )

                        OutlinedTextField(
                            value = emailValue,
                            onValueChange = {
                                emailValue = it
                            },
                            label = { Text("E-mail") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Email
                            )
                        )


                        OutlinedTextField(
                            value = passwordValue,
                            onValueChange = {
                                passwordValue = it
                            },
                            label = { Text(text = "Password") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Password,
                            ),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff
                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, description)
                                }
                            })
                        Button(
                            onClick = {
                                viewModel.login(
                                    LoginParams(
                                        email = emailValue,
                                        password = passwordValue
                                    )
                                )
                            },
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text("Zaloguj się")

                        }
                        Row() {
                            Text(
                                "Nie masz jeszcze konta? ",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text("Utwórz konto",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .clickable { navController.navigate(Screen.Register.route) })

                        }

                    }

                }
            }
        }


    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), viewModel = AuthViewModel())
}