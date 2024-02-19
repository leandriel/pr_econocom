package com.leandroid.system.pr_econocom.restaurant.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.leandroid.system.pr_econocom.R
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant

@Composable
fun FavoritesScreen(favorites: List<Restaurant>) {
    LazyColumn {
        items(favorites) { restaurant ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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