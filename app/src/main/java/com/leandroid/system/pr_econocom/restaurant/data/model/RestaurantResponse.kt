package com.leandroid.system.pr_econocom.restaurant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantResponse(
    @SerialName("location_id")
    val locationId: String = "",
    val name: String = "",
    val distance: String = "",
    val bearing: String = "",
    @SerialName("address_obj")
    val address: AddressResponse
)