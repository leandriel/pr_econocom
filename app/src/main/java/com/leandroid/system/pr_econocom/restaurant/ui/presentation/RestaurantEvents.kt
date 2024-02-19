package com.leandroid.system.pr_econocom.restaurant.ui.presentation

import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant

sealed class RestaurantEvents {
    data class GetRestaurants(val latitude: Double, val longitude: Double) : RestaurantEvents()
    data class GetRestaurantBySearch(val text: String) : RestaurantEvents()
    data class GetRestaurantDetail(val locationId: String) : RestaurantEvents()
    data class Favorites(val restaurant: Restaurant) : RestaurantEvents()
}
