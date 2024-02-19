package com.leandroid.system.pr_econocom.restaurant.domain.mapper

import com.leandroid.system.pr_econocom.restaurant.data.model.AddressResponse
import com.leandroid.system.pr_econocom.restaurant.data.model.RestaurantResponse
import com.leandroid.system.pr_econocom.restaurant.domain.model.Address
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant

fun RestaurantResponse.toRestaurant() = Restaurant(
    locationId = locationId,
    name = name,
    distance = distance,
    bearing = bearing,
    address = address.toAddress(),
    isFavorite = false
)

fun AddressResponse.toAddress() = Address(
    street1 = street1.orEmpty(),
    street2 = street2.orEmpty(),
    city = city,
    state = state,
    country = country,
    postalCode = postalCode.orEmpty(),
    addressString = addressString
)