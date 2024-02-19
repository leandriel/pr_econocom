package com.leandroid.system.pr_econocom.restaurant.data.repository

import com.leandroid.system.pr_econocom.core.data.ResponseStatus
import com.leandroid.system.pr_econocom.restaurant.data.api.RestaurantService
import com.leandroid.system.pr_econocom.restaurant.domain.mapper.toRestaurant
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant
import com.leandroid.system.pr_econocom.restaurant.domain.respository.RestaurantRepository
import io.ktor.utils.io.printStack

class RestaurantRepositoryImpl  (
    private val restaurantService: RestaurantService
): RestaurantRepository {
    override suspend fun getRestaurants(latitude: Double, longitude: Double): ResponseStatus<List<Restaurant>> = try {
        val response = restaurantService.getRestaurants(latitude, longitude)
        ResponseStatus.Success(response.data?.map { it.toRestaurant() } ?: listOf())
    } catch (e: Exception) {
        ResponseStatus.Failure(e)
    }

    override suspend fun getRestaurantsBySearch(text: String): ResponseStatus<List<Restaurant>>  = try {
        val response = restaurantService.getRestaurantsBySearch(text)
        ResponseStatus.Success(response.data?.map { it.toRestaurant() } ?: listOf())
    } catch (e: Exception) {
        ResponseStatus.Failure(e)
    }
    override suspend fun getRestaurantDetail(locationId : String): ResponseStatus<Restaurant?> = try {
        val response = restaurantService.getRestaurantDetail(locationId)
        ResponseStatus.Success(response.data?.toRestaurant() ?: null)
    } catch (e: Exception) {
        ResponseStatus.Failure(e)
    }
}
