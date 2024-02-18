package com.leandroid.system.pr_econocom.restaurant.ui.presentation

import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant

data class RestaurantState(
    val showLoading: Boolean = false,
    val restaurant: List<Restaurant> = listOf(),
    val isError: Boolean = false,
)