package com.example.spanienakanapie.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shared.data.storage.SharedSettingsHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    private val sharedSettingsHelper: SharedSettingsHelper by inject()

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        updateTokenState(sharedSettingsHelper.authToken)
        sharedSettingsHelper.setAuthTokenListener {
            updateTokenState(it)
        }
    }

    fun updateTokenState(token: String?) {
        _state.value = state.value.copy(
            loggedIn = token !== null && token.isNotEmpty()
        )
    }
}