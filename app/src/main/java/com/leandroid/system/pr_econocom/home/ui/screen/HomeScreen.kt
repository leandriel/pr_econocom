package com.leandroid.system.pr_econocom.home.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.leandroid.system.pr_econocom.restaurant.ui.screen.FavoritesScreen
import com.leandroid.system.pr_econocom.restaurant.ui.screen.Restaurant
import com.leandroid.system.pr_econocom.restaurant.ui.screen.RestaurantScreen

@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    val favorites = listOf("Favorito 1", "Favorito 2", "Favorito 3")

    Column {
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            ) {
                Text("Restaurantes")
            }
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            ) {
                Text("Favoritos")
            }
        }
        when (selectedTab) {
            0 -> RestaurantScreen()
            1 -> FavoritesScreen(favorites = favorites)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    MaterialTheme {
        HomeScreen()
    }
}