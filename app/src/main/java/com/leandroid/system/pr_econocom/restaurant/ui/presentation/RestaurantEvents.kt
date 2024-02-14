package com.leandroid.system.pr_econocom.restaurant.ui.presentation

sealed class RestaurantEvents {
    data class GetRestaurant(val latitude: Double, val longitude: Double) : RestaurantEvents()
}
