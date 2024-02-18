package com.leandroid.system.pr_econocom.restaurant.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Restaurant(
    val locationId: String,
    val name: String,
    val distance: String,
    val bearing: String,
    val address: Address
)