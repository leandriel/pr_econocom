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
    private val baseUrl = "https://api.content.tripadvisor.com/api/v1/location/"

    override suspend fun getRestaurants(latitude: Double, longitude: Double): ApiResponseData<List<RestaurantResponse>> {
        val queryParams = listOf(
            "latLong" to "$latitude,$longitude",
            "key" to apiKey
        ).joinToString("&") { (key, value) -> "$key=$value" }

        val url = baseUrl.plus("nearby_search?").plus(queryParams)

        return client.get(url).body<ApiResponseData<List<RestaurantResponse>>>()
    }

    override suspend fun getRestaurantsBySearch(text: String): ApiResponseData<List<RestaurantResponse>> {
        val queryParams = listOf(
            "key" to apiKey,
            "searchQuery" to text,
        ).joinToString("&") { (key, value) -> "$key=$value" }

        val url = baseUrl.plus("search?").plus(queryParams)

        return client.get(url).body<ApiResponseData<List<RestaurantResponse>>>()
    }

    override suspend fun getRestaurantDetail(locationId: String): ApiResponseData<RestaurantResponse> {
        val queryParams = listOf(
            "key" to apiKey,
        ).joinToString("&") { (key, value) -> "$key=$value" }

        val url = baseUrl.plus(locationId).plus("details?").plus(queryParams)

        return client.get(url).body<ApiResponseData<RestaurantResponse>>()
    }
}