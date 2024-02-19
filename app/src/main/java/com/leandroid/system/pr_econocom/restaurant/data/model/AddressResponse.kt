package com.leandroid.system.pr_econocom.restaurant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse(
    val street1: String? = null,
    val street2: String? = null,
    val city: String,
    val state: String,
    val country: String,
    @SerialName("postalcode")
    val postalCode: String? = null,
    @SerialName("address_string")
    val addressString: String
)