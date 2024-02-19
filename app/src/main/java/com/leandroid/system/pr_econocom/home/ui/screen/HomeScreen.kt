package com.leandroid.system.pr_econocom.home.ui.screen

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.leandroid.system.pr_econocom.core.data.LocationProvider
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantEvents
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantViewModel
import com.leandroid.system.pr_econocom.restaurant.ui.screen.FavoritesScreen
import com.leandroid.system.pr_econocom.restaurant.ui.screen.RestaurantScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    restaurantViewModel: RestaurantViewModel = koinViewModel(),
    goToDetail : (String) -> Unit
) {

    val restaurantState by restaurantViewModel.state.collectAsState()
    val context = LocalContext.current
    var location: Location? = null
    var selectedTab by remember { mutableStateOf(0) }
    var searchString by remember { mutableStateOf("") }
    val permission = android.Manifest.permission.ACCESS_FINE_LOCATION

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getLocation(context) {
                location = it
                restaurantViewModel.emitEvent(
                    RestaurantEvents.GetRestaurants(
                        it.latitude,
                        it.longitude
                    )
                )
            }
        } else {
            // Show dialog
        }
    }

    LaunchedEffect(Unit){
        val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            getLocation(context) {
                location = it
                restaurantViewModel.emitEvent(
                    RestaurantEvents.GetRestaurants(
                        it.latitude,
                        it.longitude
                    )
                )
            }
        } else {
            launcher.launch(permission)
        }
    }

    LaunchedEffect(searchString){
        if (searchString.isEmpty()) {
            restaurantViewModel.emitEvent(
                RestaurantEvents.GetRestaurants(
                    location?.latitude ?: 0.0,
                    location?.longitude ?: 0.0
                )
            )
        } else if (searchString.length > 3) {
            restaurantViewModel.emitEvent(RestaurantEvents.GetRestaurantBySearch(searchString))
        }
    }

    Scaffold(
        topBar = {
            Row {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = searchString, onValueChange = {
                        searchString = it
                    },
                    placeholder = {
                        Text("Search restaurants")
                    }
                )
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Restaurantes",
                        fontWeight = FontWeight.Bold
                    )
                }
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Favoritos",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            when (selectedTab) {
                0 -> RestaurantScreen(
                    restaurantState,
                    onFavorite = {
                        restaurantViewModel.emitEvent(RestaurantEvents.Favorites(it))
                    }, goToDetail
                )
                1 -> FavoritesScreen(favorites = restaurantState.favorites)
            }
        }
    }
}

private fun getLocation(context: Context, location: (Location) -> Unit){
    LocationProvider(context).getLastLocation(
        onSuccess = { location ->
            location(location)
        },
        onFailure = { errorMessage ->
            println(errorMessage)
        }
    )
}


