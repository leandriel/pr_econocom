package com.leandroid.system.pr_econocom.restaurant.ui.screen

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.content.ContextCompat
import com.leandroid.system.pr_econocom.core.data.LocationProvider
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantEvents
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantViewModel
import org.koin.androidx.compose.koinViewModel

data class Restaurant(val name: String)

@Composable
fun RestaurantScreen(viewModel: RestaurantViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    val locationProvider = LocationProvider(context)
    var latitude by remember { mutableStateOf(0.0) }
    var longitude by remember { mutableStateOf(0.0) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getLocation(context){
                viewModel.emitEvent(RestaurantEvents.GetRestaurant(it.latitude, it.longitude))
            }
        } else {
            // Show dialog
        }
    }

    LaunchedEffect(Unit){
        val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            getLocation(context){
                viewModel.emitEvent(RestaurantEvents.GetRestaurant(it.latitude, it.longitude))
            }
        } else {
            launcher.launch(permission)
        }
    }




//    LaunchedEffect(Unit) {
//        val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
//        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
//            locationProvider.getLastLocation(
//                onSuccess = { location ->
//                    latitude = location.latitude
//                    longitude = location.longitude
//                    viewModel.emitEvent(RestaurantEvents.GetRestaurant(latitude, longitude))
//                },
//                onFailure = { errorMessage ->
//                    println(errorMessage)
//                }
//            )
//        } else {
//            launcher.launch(permission)
//        }
//    }



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