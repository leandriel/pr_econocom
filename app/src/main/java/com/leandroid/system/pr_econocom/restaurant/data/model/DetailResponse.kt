package com.leandroid.system.pr_econocom.restaurant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailResponse(
    @SerialName("location_id")
    val locationId: String = "",
    val name: String = "",
    val description: String = "",
    @SerialName("web_url")
    val webUrl: String = "",
    @SerialName("address_obj")
    val address: AddressResponse
)