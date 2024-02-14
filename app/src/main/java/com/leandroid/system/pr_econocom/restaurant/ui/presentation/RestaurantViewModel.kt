package com.leandroid.system.pr_econocom.restaurant.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leandroid.system.pr_econocom.core.data.ResponseStatus
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant
import com.leandroid.system.pr_econocom.restaurant.domain.usecase.GetRestaurantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RestaurantViewModel (
    private val getRestaurant: GetRestaurantsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RestaurantState())
    val state: StateFlow<RestaurantState>
        get() = _state.asStateFlow()

    fun emitEvent(event: RestaurantEvents) {
        when (event) {
            is RestaurantEvents.GetRestaurant -> getRestaurant(event.latitude, event.longitude)
        }
    }

    private fun getRestaurant(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            getRestaurant.execute(
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
                restaurant = restaurant,
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