package com.example.spanienakanapie.itinerary

import android.util.Log
import com.example.spanienakanapie.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mapbox.geojson.Point
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.logD
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.annotation.AnnotationManagerImpl
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateBearing
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateOptions
import com.mapbox.search.ReverseGeoOptions
import com.mapbox.search.SearchEngine
import com.mapbox.search.SearchEngineSettings
import com.mapbox.search.autocomplete.PlaceAutocomplete
import com.mapbox.search.autocomplete.PlaceAutocompleteSuggestion
import kotlinx.coroutines.delay
import androidx.compose.ui.res.stringResource
import com.mapbox.search.ResponseInfo
import com.mapbox.search.SearchCallback
import com.mapbox.search.result.SearchResult

@OptIn(MapboxExperimental::class)
@Composable
fun PickLocationScreen(){
    val mapViewportState = rememberMapViewportState(){
        setCameraOptions(){
            pitch(0.0)
            zoom(14.0)

        }
    }
    

    val placeAutocomplete = PlaceAutocomplete.create()
    val density = LocalDensity.current.density
    var clickedPoint by remember { mutableStateOf<Point?>(null) }
    var place by remember {
        mutableStateOf<List<PlaceAutocompleteSuggestion>>(emptyList())
    }
    val searchEngine = SearchEngine.createSearchEngineWithBuiltInDataProviders(
        SearchEngineSettings()
    )
     val searchCallback = object : SearchCallback {
        override fun onResults(results: List<SearchResult>, responseInfo: ResponseInfo) {
            if (results.isEmpty()) {
                Log.i("SearchApiExample", "No reverse geocoding results")
            } else {
                Log.i("SearchApiExample", "Reverse geocoding results: $results")
                results.forEach { searchResult->
                    searchResult.fullAddress
                    searchResult.name
                    Log.i("searchReuslt", searchResult.fullAddress.toString())
                }
            }
        }

        override fun onError(e: Exception) {
            Log.i("SearchApiExample", "Reverse geocoding error", e)
        }
    }


    LaunchedEffect (clickedPoint){
        Log.d("point", clickedPoint?.coordinates().toString())
        clickedPoint?.let{
            val reverseGeoCode = ReverseGeoOptions(
                center = Point.fromLngLat(it.longitude(), it.latitude()),
                limit = 1
            )
            val searchRequestTask = searchEngine.search(reverseGeoCode, searchCallback)
            Log.d("Reverse point", searchRequestTask.toString())

        }


//        clickedPoint?.let {
//            val response = placeAutocomplete.suggestions(Point.fromLngLat(it.longitude(), it.latitude()))
//            Log.d("point", Point.fromLngLat(it.longitude(), it.latitude()).toString())
//            response.onValue {list ->
//                list.forEach {
//                    if (place.size <= 2){
//                        Log.d("placeAutocomplete", it.toString())
//                        place = list
//                    }
//                }
//
//            }
//        }


    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp),
    ) {
        Column(modifier = Modifier.padding(it)) {
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
                    val locationComponentPlugin = mapView.location.apply {
                        // Enable the location component
                        this.enabled = true
                    }
                    mapView.mapboxMap.addOnMapClickListener{point ->
                        clickedPoint = point
                        true
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
        }
    }
}