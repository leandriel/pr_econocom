package com.leandroid.system.pr_econocom.restaurant.data.repository

import com.leandroid.system.pr_econocom.core.data.ResponseStatus
import com.leandroid.system.pr_econocom.restaurant.data.api.RestaurantService
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant
import com.leandroid.system.pr_econocom.restaurant.domain.respository.RestaurantRepository
import io.ktor.utils.io.printStack

class RestaurantRepositoryImpl  (
    private val restaurantService: RestaurantService
): RestaurantRepository {
    override suspend fun getRestaurants(latitude: Double, longitude: Double): ResponseStatus<List<Restaurant>> = try {
        val response = restaurantService.getRestaurantData(latitude, longitude)
        ResponseStatus.Success(response.data ?: listOf())
    } catch (e: Exception) {
        println("asdasdasdasdasdasdasd"+e.printStack())
        ResponseStatus.Failure(e)

    }
}
