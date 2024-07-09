package com.example.spanienakanapie.authorization

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shared.data.models.LoginParams
import com.example.spanienakanapie.navigation.Screen
import com.example.spanienakanapie.viewmodels.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spanienakanapie.viewmodels.Event

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()){

    val state by viewModel.state.collectAsState()

    var emailValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }

    
    LaunchedEffect(state.event) {
        state.event?.consume{
            when(it){
               is Event.NavigateEvent -> {

               }
            }
        }
    }


    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .consumeWindowInsets(it)
                .systemBarsPadding()
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
                            .padding(top = 16.dp,
                                bottom = 32.dp)
                    )

                    OutlinedTextField(
                        value = emailValue,
                        onValueChange = {
                            viewModel.setEmail(it)
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
                        viewModel.setPassword(it) },
                        label = { Text(text = "Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Password,
                        ),
                        visualTransformation = VisualTransformation { PasswordValue ->
                            TransformedText(PasswordValue, OffsetMapping.Identity)
                        })
                    Button(onClick = { viewModel.login(LoginParams(email = emailValue, password = passwordValue)) },
                        modifier = Modifier
                            .padding(16.dp)) {
                        Text("Zaloguj się")

                    }
                    Row(){
                        Text("Nie masz jeszcze konta? ",
                            style = MaterialTheme.typography.bodyMedium)

                            Text("Utwórz konto",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .clickable { navController.navigate(Screen.Register.route)})

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