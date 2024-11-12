package com.example.spanienakanapie.Post


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.data.models.PickPlace
import com.example.shared.data.models.Post
import com.example.shared.data.repositories.PostRepository
import com.example.shared.data.utils.Resource
import com.example.spanienakanapie.R
import com.example.spanienakanapie.utils.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.example.spanienakanapie.viewmodels.Event

class PostViewModel: ViewModel(), KoinComponent  {

    private val repository: PostRepository by inject()

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
    fun setCity(value: String){
        _state.value = state.value.copy(city = value)
    }


    fun pickPlace(pick: PickPlace){
        viewModelScope.launch {
            _state.value = state.value.copy(
                placeName = pick.name,
                placeAddress = pick.address,
                city = pick.city
            )

        }
    }

    fun addPost(){
        val post = state.value.placeAddress?.let {address ->
            state.value.placeName?.let { name ->
                state.value.city?.let { city ->
                    Post(
                        id = null,
                        title = state.value.title,
                        content = state.value.content,
                        locationName = name,
                        address = address,
                        city = city
                    )
                }
            }
        }
        viewModelScope.launch{
                repository.createPost(post!!)
        }
    }

    fun getPosts(){
        viewModelScope.launch {
            when(val result = state.value.city?.let { repository.getPosts(it) }){
                is Resource.Success ->{
                    Log.d("Resource", "Success")
                    _state.value = state.value.copy(
                        posts = result.data!!
                    )
                }
                is Resource.Error ->{
                    Log.d("Resource", "Error")
                    _state.value = state.value.copy(
                        event = Event.SnackbarEvent(
                            UiText.StringResource(R.string.cant_load_posts_try_again).toString()
                        )
                    )
                }

                null -> TODO()
            }
        }
    }

    fun getCities(){
        viewModelScope.launch {
            when(val result = repository.getCity()){
                is Resource.Success ->{
                    Log.d("Resource", "Success")
                    _state.value = state.value.copy(
                        cities = result.data!!
                    )
                }
                is Resource.Error ->{
                    Log.d("Resource", "Error")
                    _state.value = state.value.copy(
                        event = Event.SnackbarEvent(
                            UiText.StringResource(R.string.cant_load_posts_try_again).toString()
                        )
                    )
                }


            }
        }
    }
}