package com.example.spanienakanapie.viewmodels


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.data.models.LoginParams
import com.example.shared.data.models.RegisterParams
import com.example.shared.data.repositories.AuthRepository
import com.example.shared.data.utils.Resource
import com.example.spanienakanapie.R
import com.example.spanienakanapie.utils.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AuthViewModel : ViewModel(), KoinComponent {
    private val repository: AuthRepository by inject()

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun setName(value:String){
        _state.value = state.value.copy(name = value)
    }
    fun setEmail(value:String){
        _state.value = state.value.copy(email = value)
    }
    fun setPassword(value:String){
        _state.value = state.value.copy(password = value)
    }

    fun register(registerParams: RegisterParams){
        viewModelScope.launch {
            val result = repository.register(registerParams)
            when(result){
                is Resource.Success ->{
                    Log.d("Registration", "Success")
                    _state.value = state.value.copy(
                        //event = Event.NavigateEvent("HomeScreen")
                    )
                }
                is Resource.Error -> {
                    Log.d("Registration", result.message!!)
                    _state.value = state.value.copy(
                        event = Event.SnackbarEvent(UiText.StringResource(R.string.register_failed).toString()))
                }
            }
        }
    }

    fun login(loginParams: LoginParams){
        viewModelScope.launch {
            val result = repository.login(loginParams)
            when(result) {
                is Resource.Success -> {
                    Log.d("Login", "Success")
                    _state.value = state.value.copy(
                        //event = Event.NavigateEvent("HomeScreen")
                    )
                }

                is Resource.Error -> {
                    Log.d("Login", result.message!!)
                    _state.value = state.value.copy(
                        event = Event.SnackbarEvent(
                            UiText.StringResource(R.string.login_filed).toString()
                        )
                    )
                }
            }

        }
    }
}

