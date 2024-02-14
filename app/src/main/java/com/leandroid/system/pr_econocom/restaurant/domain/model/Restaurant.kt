package com.leandroid.system.pr_econocom.restaurant.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    @SerialName("location_id")
    val locationId: String = "",
    val name: String = "",
    val distance: String = "",
    val bearing: String = "",
    @SerialName("address_obj")
    val addressObj: Address = Address("","","","","","","")
)