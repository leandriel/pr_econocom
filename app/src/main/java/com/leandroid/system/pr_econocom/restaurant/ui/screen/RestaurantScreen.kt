package com.leandroid.system.pr_econocom.restaurant.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.leandroid.system.pr_econocom.core.data.LocationProvider
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantEvents
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantViewModel
import org.koin.androidx.compose.koinViewModel

data class Restaurant(val name: String)

@Composable
fun RestaurantScreen(viewModel: RestaurantViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val locationProvider = LocationProvider(context)
    var latitude by remember { mutableStateOf(0.0) }
    var longitude by remember { mutableStateOf(0.0) }

    LaunchedEffect(Unit) {
        locationProvider.getLastLocation(
            onSuccess = { location ->
                latitude = location.latitude
                longitude = location.longitude
                viewModel.emitEvent(RestaurantEvents.GetRestaurant(latitude, longitude))
            },
            onFailure = { errorMessage ->
            }
        )
    }

    LazyColumn {
        items(state.restaurant) { restaurant ->
            Text(
                text = restaurant.name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}