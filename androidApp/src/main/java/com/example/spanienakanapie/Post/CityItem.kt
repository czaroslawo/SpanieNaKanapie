package com.example.spanienakanapie.Post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CityItem(
    city: String,
    isExpended: Boolean,
    onItemClicked: () -> Unit){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked() }
            .padding(16.dp)) {

            Text(
                text = city,
                style = MaterialTheme.typography.bodyLarge
            )
           Box(contentAlignment = Alignment.CenterEnd) {
                if (isExpended) {
                       Icon(Icons.Default.KeyboardArrowDown, contentDescription = "")
                   }else{
                       Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "")
                }

           }

        }
    }

}