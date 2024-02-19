package com.leandroid.system.pr_econocom.restaurant.ui.screen

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.leandroid.system.pr_econocom.R
import com.leandroid.system.pr_econocom.core.data.LocationProvider
import com.leandroid.system.pr_econocom.home.ui.components.FullScreenLoading
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantEvents
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantState
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantViewModel
import com.leandroid.system.pr_econocom.ui.theme.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreen(locationId: String? = null, state: RestaurantState, onFavorite: (Restaurant) -> Unit, goToDetail : (String) -> Unit) {
    if (state.showLoading) {
        FullScreenLoading()
    }
    LazyColumn {
        items(state.restaurants) { restaurant ->
                Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = {
                    goToDetail(Screen.Detail.route + "/${restaurant.locationId}")
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F),
                            text = restaurant.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Image(
                            modifier = Modifier.clickable {
                                onFavorite.invoke(restaurant)
                            },
                            painter = painterResource(id = if (restaurant.isFavorite) R.drawable.ic_start_filled else R.drawable.ic_start),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = restaurant.address.addressString,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}