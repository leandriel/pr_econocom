package com.leandroid.system.pr_econocom.restaurant.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leandroid.system.pr_econocom.core.data.ResponseStatus
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant
import com.leandroid.system.pr_econocom.restaurant.domain.usecase.GetRestaurantDetailUseCase
import com.leandroid.system.pr_econocom.restaurant.domain.usecase.GetRestaurantsBySearchUseCase
import com.leandroid.system.pr_econocom.restaurant.domain.usecase.GetRestaurantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RestaurantViewModel(
    private val getRestaurants: GetRestaurantsUseCase,
    private val getRestaurantsBySearch: GetRestaurantsBySearchUseCase,
    private val getRestaurantDetail: GetRestaurantDetailUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RestaurantState(showLoading = true))
    val state: StateFlow<RestaurantState>
        get() = _state.asStateFlow()

    fun emitEvent(event: RestaurantEvents) {
        when (event) {
            is RestaurantEvents.GetRestaurants -> getRestaurants(event.latitude, event.longitude)
            is RestaurantEvents.GetRestaurantBySearch -> getRestaurantsBySearch(event.text)
            is RestaurantEvents.GetRestaurantDetail -> getRestaurantsDetail(event.locationId)
            is RestaurantEvents.Favorites -> favorites(event.restaurant)
        }
    }

    private fun getRestaurants(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            getRestaurants.execute(
                GetRestaurantsUseCase.Params(
                    latitude,
                    longitude
                )
            ).collect { result ->
                when (result) {
                    is ResponseStatus.Loading -> handleLoading(result.loading)
                    is ResponseStatus.Success -> {
                        updateRestaurant(result.data)
                    }

                    is ResponseStatus.Failure -> {
                        updateError()
                    }
                }
            }
        }
    }

    private fun getRestaurantsBySearch(text: String) {
        viewModelScope.launch {
            getRestaurantsBySearch.execute(text).collect { result ->
                when (result) {
                    is ResponseStatus.Loading -> handleLoading(result.loading)
                    is ResponseStatus.Success -> {
                        updateRestaurant(result.data)
                    }

                    is ResponseStatus.Failure -> {
                        updateError()
                    }
                }
            }
        }
    }

    private fun getRestaurantsDetail(locationId: String) {
        viewModelScope.launch {
            getRestaurantDetail.execute(locationId).collect { result ->
                when (result) {
                    is ResponseStatus.Loading -> handleLoading(result.loading)
                    is ResponseStatus.Success -> {
                        updateRestaurantDetail(result.data)
                    }

                    is ResponseStatus.Failure -> {
                        updateError()
                    }
                }
            }
        }
    }

    private fun favorites(restaurant: Restaurant) {
        viewModelScope.launch {
            val favorites = _state.value.favorites.toMutableList()
            val restaurants = _state.value.restaurants.toMutableList()
            if (restaurant.isFavorite) {
                restaurants.map { if(it.locationId == restaurant.locationId) it.copy(isFavorite = true) else it }
                updateFavorites(restaurants, favorites.filter { it.locationId != restaurant.locationId })
            } else {
                restaurants.map { if(it.locationId == restaurant.locationId) it.copy(isFavorite = false) else it }
                favorites.add(restaurant)
                updateFavorites(restaurants, favorites)
            }
        }
    }

    private fun handleLoading(show: Boolean) {
        _state.update {
            it.copy(
                showLoading = show,
            )
        }
    }

    private fun updateRestaurant(restaurant: List<Restaurant>) {
        _state.update {
            it.copy(
                restaurants = restaurant,
                isError = false
            )
        }
    }

    private fun updateFavorites(restaurants: List<Restaurant>, favorites: List<Restaurant>) {
        _state.update {
            it.copy(
                restaurants = restaurants,
                favorites = favorites,
                isError = false
            )
        }
    }

    private fun updateRestaurantDetail(restaurant: Restaurant?) {
        _state.update {
            it.copy(
                restaurantDetail = restaurant,
                isError = false
            )
        }
    }

    private fun updateError() {
        _state.update {
            it.copy(
                showLoading = false,
                isError = true
            )
        }
    }
}