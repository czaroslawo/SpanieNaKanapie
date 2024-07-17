package com.example.spanienakanapie.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.spanienakanapie.model.BottomNavItem


@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
    items: List<BottomNavItem>,
    bottomBarState: MutableState<Boolean>
) {
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backstackEntry.value?.destination
    val bottomBarOffset by animateDpAsState(
        targetValue = if (bottomBarState.value) 0.dp else 150.dp, //fajnie by tu zrobić żeby liczło po prostu wielkość bottombaru
        label = ""
    )

    NavigationBar(
        modifier = modifier
            .offset(y = bottomBarOffset)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = { onItemClick(item) },
                label = { Text(item.name) },
                icon = { Icon(item.icon, contentDescription = null) }
            )
        }
    }

}