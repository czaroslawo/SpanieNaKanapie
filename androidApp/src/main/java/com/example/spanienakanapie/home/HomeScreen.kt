package com.example.spanienakanapie.home

import android.graphics.Rect
import android.location.Address
import android.util.Log
import android.view.RoundedCorner
import android.view.View
import android.view.Window
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shared.data.models.SugestetdPlace
import com.example.spanienakanapie.R
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateBearing
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateOptions
import com.mapbox.search.autocomplete.PlaceAutocomplete
import com.mapbox.search.autocomplete.PlaceAutocompleteSuggestion
import com.mapbox.search.autocomplete.PlaceAutocompleteType
import com.mapbox.search.ui.view.CommonSearchViewConfiguration
import com.mapbox.search.ui.view.DistanceUnitType
import java.util.Calendar
import com.mapbox.search.ui.view.SearchResultsView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@MapboxExperimental
@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {

    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val mapViewportState = rememberMapViewportState(){
        setCameraOptions(){
            pitch(0.0)
            zoom(14.0)

        }
    }
    val density = LocalDensity.current.density
    val placeAutocomplete = PlaceAutocomplete.create()

    var place by remember{
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val paddingHorizontal by animateDpAsState(
        targetValue = if (!expanded) 10.dp else 0.dp,
        animationSpec = tween(durationMillis = 5000)
    )
    var suggestions by remember {
        mutableStateOf<List<PlaceAutocompleteSuggestion>>(emptyList())
    }






    LaunchedEffect(place){
            val response = placeAutocomplete.suggestions(place)
            response.onValue {
                Log.d("lista", it.toString())
                 suggestions = it

        }

    }


    
    

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp),
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MapboxMap(
                    modifier = Modifier.fillMaxSize(),
                    mapViewportState = mapViewportState,
                    compass = {
                        Compass(modifier = Modifier
                            .height(0.dp)
                            .width(0.dp))
                    },
                    scaleBar = {
                        ScaleBar(height = 0.dp,
                                    primaryColor = Color.Transparent,
                                    secondaryColor = Color.Transparent,
                                    textColor = Color.Transparent,
                                    borderWidth = 0.dp,
                                    textBorderWidth = 0.dp,
                            textSize = 0.sp
                        )
                    }


                ) {
                    MapEffect(Unit) { mapView ->
                        mapView.location.updateSettings {
                            locationPuck = createDefault2DPuck(withBearing = true)
                            enabled = true
                            puckBearing = PuckBearing.COURSE
                            puckBearingEnabled = true
                        }

                        mapViewportState.transitionToFollowPuckState(
                            followPuckViewportStateOptions = FollowPuckViewportStateOptions.Builder()
                                .bearing(FollowPuckViewportStateBearing.Constant(0.0))
                                .padding(EdgeInsets(200.0 * density, 0.0, 0.0, 0.0))
                                .pitch(5.0)
                                .zoom(15.0)
                                .build(),
                        ) { success ->

                        }

                    }
                }

                SearchBar(
                    inputField = {
                             SearchBarDefaults.InputField(
                                 query = place,
                                 onQueryChange = {place = it},
                                 onSearch = {expanded = false},
                                 expanded = expanded,
                                 onExpandedChange = {expanded = it},
                                 placeholder = { Text(text = stringResource(R.string.find_place))},
                                 leadingIcon = { Icon(Icons.Default.Search, contentDescription = null)},
                                 trailingIcon = {
                                     if(place.isNotEmpty()){
                                         IconButton(onClick = {place = ""},
                                             content = {Icon(Icons.Outlined.Cancel, contentDescription = null)})
                                 } },
                             )
                    },
                    expanded = expanded,
                    onExpandedChange = {expanded = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (!expanded) {
                                Modifier
                                    .systemBarsPadding()
                                    .padding(horizontal = 10.dp)
                            } else {
                                Modifier.clip(RoundedCornerShape(8.dp))

                            }
                        ),
                    colors = SearchBarColors(containerColor = MaterialTheme.colorScheme.background, dividerColor = MaterialTheme.colorScheme.primary),
                    shadowElevation = 4.dp,
                    content = {
                        LazyColumn {
                            items(if (suggestions.isEmpty()) 0 else suggestions.size){suggestion ->
                                val distanceInMeters = suggestions[suggestion].distanceMeters
                                val formattedDistance = formatDistance(distanceInMeters)
                                SearchSuggestionItem(place = SugestetdPlace(
                                    suggestions[suggestion].name,
                                    suggestions[suggestion].formattedAddress,
                                    distance = formattedDistance))
                            }
                        }

                    }
                )

                    SearchResultsView.Configuration(
                        CommonSearchViewConfiguration(DistanceUnitType.METRIC)
                    )

            }})
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
fun formatDistance(distanceInMeters: Double?): String {
    return when {
        distanceInMeters == null -> ""
        distanceInMeters >= 1000 && distanceInMeters < 10000 -> {
            // Convert to km with one decimal place
            "${(distanceInMeters / 1000.0).format(1)} km"
        }
        distanceInMeters >= 10000 -> {
            // Convert to km without decimal places
            "${distanceInMeters.toInt() / 1000} km"
        }
        else -> {
            // Round to the nearest 50 meters
            "${(distanceInMeters.toInt() / 50) * 50} m"
        }
    }
}
@Preview
@MapboxExperimental
@ExperimentalMaterial3Api
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}