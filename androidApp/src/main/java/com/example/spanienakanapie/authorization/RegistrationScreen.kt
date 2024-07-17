package com.example.spanienakanapie.authorization


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.shared.data.models.RegisterParams
import com.example.spanienakanapie.viewmodels.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.spanienakanapie.viewmodels.Event
import kotlinx.coroutines.launch

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun RegistrationPreview(){
    RegistrationScreen(navController = rememberNavController())
}

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
){

    val state by viewModel.state.collectAsState()

    var nameValue by remember {
        mutableStateOf("")
    }
    var emailValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }
    var emailValidation by remember {
        mutableStateOf("")
    }
    var confirmPasswordValue by remember {
        mutableStateOf("")
    }
    var passwordValidation by remember {
        mutableStateOf("")
    }
    var passwordConfirmValidation by remember {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var confirmPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var passwordValidationChecked by remember {
        mutableStateOf(false)
    }
    var emailValidationChecked by remember {
        mutableStateOf(false)
    }
    var samePasswordValidationChecked by remember {
        mutableStateOf(false)
    }
    val snackBarsState = remember {
        SnackbarHostState()
    }

    val scrollState = rememberScrollState()
    LaunchedEffect(state.event) {
        state.event?.consume {
            when (it) {
                is Event.NavigateEvent -> {

                }

                is Event.SnackbarEvent -> {
                    launch {
                        snackBarsState.showSnackbar(message = it.message)
                    }
                }
            }
        }
    }



    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { innerPadding ->

        Column(Modifier.padding(innerPadding)
            ) {
            Log.d("inner padding", innerPadding.toString())
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)




            ) {
                Box(modifier = Modifier
                    .imePadding()
                    .height(480.dp)) {

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(480.dp)
                        .padding(horizontal = 16.dp)

                        ,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )

                ) {


                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)



                        )
                        {


                            Text(
                                text = "Stwórz konto",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(top = 16.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(32.dp),
                            )


                            OutlinedTextField(value = nameValue,
                                onValueChange = {
                                    nameValue = it
                                }, label = { Text(text = "Nazwa użytkownika") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(),
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Email
                                )

                            )
                            Box(
                                contentAlignment = Alignment.BottomStart,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp),
                            ) {
                                Text(
                                    text = emailValidation,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier
                                        .padding()
                                )
                            }

                            OutlinedTextField(value = emailValue,
                                onValueChange = {
                                    emailValue = it
                                }, label = { Text(text = "E-mail") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(),
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Email
                                )

                            )


                            Box(
                                contentAlignment = Alignment.BottomStart,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp),
                            ) {
                                Text(
                                    text = passwordValidation,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier
                                        .padding()
                                )
                            }

                            OutlinedTextField(value = passwordValue,
                                onValueChange = {
                                    passwordValue = it
                                },
                                label = { Text(text = "Password") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(),
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Password
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
                                }

                            )


                            Box(
                                contentAlignment = Alignment.BottomStart,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp),
                            ) {
                                Text(
                                    text = passwordConfirmValidation,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier
                                        .padding()
                                )
                            }



                            OutlinedTextField(value = confirmPasswordValue,
                                onValueChange = {
                                    confirmPasswordValue = it
                                }, label = { Text(text = "Confirm Password") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(),
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Password
                                ),
                                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                trailingIcon = {
                                    val image = if (confirmPasswordVisible)
                                        Icons.Filled.Visibility
                                    else Icons.Filled.VisibilityOff
                                    val description =
                                        if (confirmPasswordVisible) "Hide password" else "Show password"
                                    IconButton(onClick = {
                                        confirmPasswordVisible = !confirmPasswordVisible
                                    }) {
                                        Icon(imageVector = image, description)
                                    }
                                }

                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Button(
                                    onClick = {
                                        if (emailValue.isNotEmpty()) {
                                            if (emailValue.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())) {
                                                emailValidation = ""
                                                emailValidationChecked = true
                                            } else {
                                                emailValidation = "e-mail jest nie poprawny"
                                                emailValidationChecked = false
                                            }
                                        } else {
                                            emailValidation = "e-mail nie może być pusty"
                                            emailValidationChecked = false
                                        }

                                        if (passwordValue.isEmpty()) {
                                            passwordValidation = "Hasło nie może być puste"
                                            passwordValidationChecked = false
                                        } else if (passwordValue.length < 8) {
                                            passwordValidation =
                                                "Hasło nie może zawierać mniej niż 8 znaków"
                                            passwordValidationChecked = false
                                        } else if (passwordValue.any { it.isUpperCase() } == false) {
                                            passwordValidation =
                                                "Hasło musi zawierać conajmniej jedną wielką literę"
                                            passwordValidationChecked = false
                                        } else if (!passwordValue.contains("[0-9]".toRegex())) {
                                            passwordValidation =
                                                "Hasło musi zawierać conajmniej jedną cyfrę"
                                            passwordValidationChecked = false
                                        } else {
                                            passwordValidation = ""
                                            passwordValidationChecked = true
                                        }
                                        if (passwordValue != confirmPasswordValue) {
                                            passwordConfirmValidation = "hasła nie są takie same"
                                            samePasswordValidationChecked = false
                                        } else {
                                            passwordConfirmValidation = ""
                                            samePasswordValidationChecked = true
                                        }

                                        if (emailValidationChecked == true && passwordValidationChecked == true && samePasswordValidationChecked == true) {
                                            viewModel.register(
                                                RegisterParams(
                                                    name = nameValue,
                                                    email = emailValue,
                                                    password = passwordValue
                                                )
                                            )
                                        }

                                    },
                                    modifier = Modifier
                                        .height(40.dp)
                                ) {
                                    Text(text = "Zarejestruj się")
                                }
                                Log.d("inner padding", innerPadding.toString())


                            }

                        }
                    }

                }
            }
        }

    }


}

