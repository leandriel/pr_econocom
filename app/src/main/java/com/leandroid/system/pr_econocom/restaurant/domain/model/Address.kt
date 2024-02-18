package com.leandroid.system.pr_econocom.restaurant.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Address(
    val street1: String,
    val street2: String,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String,
    val addressString: String
)
