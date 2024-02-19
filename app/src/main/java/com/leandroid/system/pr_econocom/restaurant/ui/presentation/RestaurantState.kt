package com.leandroid.system.pr_econocom.restaurant.ui.presentation

import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant

data class RestaurantState(
    val showLoading: Boolean = false,
    val restaurantDetail: Restaurant? = null,
    val restaurants: List<Restaurant> = listOf(),
    val favorites: List<Restaurant> = listOf(),
    val isError: Boolean = false
)