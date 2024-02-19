package com.leandroid.system.pr_econocom.restaurant.data.api

import com.leandroid.system.pr_econocom.core.data.ApiResponseData
import com.leandroid.system.pr_econocom.core.data.httpClient
import com.leandroid.system.pr_econocom.restaurant.data.model.RestaurantResponse
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant

interface RestaurantService {
    suspend fun getRestaurants(latitude: Double, longitude: Double): ApiResponseData<List<RestaurantResponse>>
    suspend fun getRestaurantsBySearch(text: String): ApiResponseData<List<RestaurantResponse>>
    suspend fun getRestaurantDetail(locationId : String): ApiResponseData<RestaurantResponse>

    companion object Factory {
        fun build(): RestaurantService {
            return RestaurantServiceImpl(
                client = httpClient
            )
        }
    }
}