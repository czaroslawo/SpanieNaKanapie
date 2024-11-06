package com.example.spanienakanapie.itinerary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.data.models.PickPlace
import com.example.spanienakanapie.authorization.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class PostViewModel: ViewModel(), KoinComponent  {

    private val _state = MutableStateFlow(PostState())
    val state = _state.asStateFlow()

    fun setPlaceName(value:String){
        _state.value = state.value.copy(placeName = value)
    }
    fun setPlaceAddress(value:String){
        _state.value = state.value.copy(placeAddress = value)
    }
    fun setContent(value:String){
        _state.value = state.value.copy(content = value)
    }
    fun setTitle(value:String){
        _state.value = state.value.copy(title = value)
    }


    fun pickPlace(pick: PickPlace){
        viewModelScope.launch {
            _state.value = state.value.copy(
                placeName = pick.name,
                placeAddress = pick.address
            )

        }
    }

    fun addArticle(){

    }
}