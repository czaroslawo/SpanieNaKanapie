package com.example.spanienakanapie.itinerary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spanienakanapie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPostScreen(){

    Scaffold(topBar = {
        CenterAlignedTopAppBar(navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ArrowBack, "")}},
            title = { Text(text = stringResource(R.string.create_article), color = MaterialTheme.colorScheme.primary) },
            actions = { IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Check, "")
                    }}
            )
    }) {
        Column(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                
            }
        }
    }
}

@Composable
@Preview
fun NewPostPreview(){
    NewPostScreen()
}