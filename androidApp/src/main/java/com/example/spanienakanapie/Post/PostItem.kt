package com.example.spanienakanapie.Post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostItem(
    title: String,
    locationName: String
){
    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)) {
        Text(text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(vertical = 8.dp))
        Text(text = locationName,
            style = MaterialTheme.typography.bodySmall)

    }
}