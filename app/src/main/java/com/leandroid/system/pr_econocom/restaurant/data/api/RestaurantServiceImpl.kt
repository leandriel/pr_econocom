package com.leandroid.system.pr_econocom.restaurant.data.api

import com.leandroid.system.pr_econocom.core.data.ApiResponseData
import com.leandroid.system.pr_econocom.restaurant.data.model.RestaurantResponse
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.request.get

class RestaurantServiceImpl (private val client: HttpClient) : RestaurantService {
    private val apiKey = "D64A83177C9B41E1A7AF4F098E74E17A"
    private val baseUrl = "https://api.content.tripadvisor.com/api/v1/location/nearby_search?"

    override suspend fun getRestaurantData(latitude: Double, longitude: Double): ApiResponseData<List<RestaurantResponse>> {
        val url = buildUrl(latitude, longitude)
        return client.get(url).body<ApiResponseData<List<RestaurantResponse>>>()
    }

    private fun buildUrl(latitude: Double, longitude: Double): String {
        val queryParams = listOf(
            "latLong" to "$latitude,$longitude",
            "key" to apiKey
        )
        return baseUrl + queryParams.joinToString("&") { (key, value) -> "$key=$value" }
    }
}