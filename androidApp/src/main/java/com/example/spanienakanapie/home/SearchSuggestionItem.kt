package com.example.spanienakanapie.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shared.data.models.SugestetdPlace

@Composable
fun SearchSuggestionItem(
    place: SugestetdPlace
){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)) {
        Icon(Icons.Outlined.LocationOn, contentDescription = null,
            modifier = Modifier.padding(start = 16.dp, end = 25.dp))
        Column(modifier = Modifier.weight(3f)) {
            Text(text = place.name,
                fontSize = 16.sp,
                modifier = Modifier.padding( bottom = 2.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
            place.address?.let {
                Text(text = it,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis) }
        }
        Box(contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                .weight(1f)){
            place.distance?.let {
                Text(text = it,
        //            if(place.distance != null) "${place.distance} km" else "",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
            }
        }

    }
}

@Preview
@Composable
fun SearchSuggestionsItemPreview(){
    SearchSuggestionItem(SugestetdPlace("Koloseum", "Piazza del Colloseooooooooooooooooooooooooooooo", "1528"))
}