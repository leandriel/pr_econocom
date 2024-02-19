package com.leandroid.system.pr_econocom.restaurant.domain.model


data class Restaurant(
    val locationId: String,
    val name: String,
    val distance: String,
    val bearing: String,
    val address: Address,
    var isFavorite: Boolean
)