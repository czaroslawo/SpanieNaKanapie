package com.example.spanienakanapie.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shared.data.models.RegisterParams
import com.example.spanienakanapie.viewmodels.AuthViewModel

@ExperimentalMaterial3Api
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: AuthViewModel
){


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



    Scaffold { innerPadding ->

        Column(Modifier.padding(innerPadding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .padding(horizontal = 16.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                    {


                        Text(
                            text = "Stwórz konto",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Box(
                            contentAlignment = Alignment.BottomStart,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp),
                        ) {
                            Text(
                                text = emailValidation,
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier
                                    .padding()
                            )
                        }

                        OutlinedTextField(value = nameValue,
                            onValueChange = {
                                viewModel.setName(it)
                        }, label = { Text(text = "Nazwa użytkownika") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(),
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Email
                            )

                        )

                        OutlinedTextField(value = emailValue,
                            onValueChange = {
                                viewModel.setEmail(it)
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
                                viewModel.setPassword(it)
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
                                        viewModel.register(RegisterParams(
                                            name = nameValue,
                                            email = emailValue,
                                            password = passwordValue
                                        ))
                                    }

                                },
                                modifier = Modifier
                                    .height(40.dp)
                            ) {
                                Text(text = "Zarejestruj się")
                            }


                        }

                    }


                }
            }
        }

    }


}