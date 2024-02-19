package com.leandroid.system.pr_econocom.restaurant.domain.model

data class Address(
    val street1: String,
    val street2: String,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String,
    val addressString: String
)
