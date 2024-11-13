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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.spanienakanapie.R

@Composable
fun CityItem(
    city: String,
    isExpended: Boolean,
    onItemClicked: () -> Unit){

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onItemClicked() }
            .drawBehind {
                val y = size.height
                drawLine(
                    Color.LightGray,
                    Offset(0f, y),
                    Offset(size.width, y)
                )
            }
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Icon(painter = painterResource(id = R.drawable.location_pin),
                contentDescription = "",
                modifier = Modifier.height(25.dp))

            Text(
                text = city,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
           Box(contentAlignment = Alignment.CenterEnd,
           modifier = Modifier.weight(1f)) {
                if (isExpended) {
                       Icon(Icons.Default.KeyboardArrowDown, contentDescription = "")
                   }else{
                       Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "")
                }

           }

        }
    }

}