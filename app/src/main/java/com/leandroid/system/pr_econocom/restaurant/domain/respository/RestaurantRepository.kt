package com.leandroid.system.pr_econocom.restaurant.domain.respository

import com.leandroid.system.pr_econocom.core.data.ResponseStatus
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurants(latitude: Double, longitude: Double): ResponseStatus<List<Restaurant>>
}