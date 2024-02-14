package com.leandroid.system.pr_econocom.restaurant.domain.usecase

import com.leandroid.system.pr_econocom.core.data.ResponseStatus
import com.leandroid.system.pr_econocom.core.domain.BaseUseCase
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant
import com.leandroid.system.pr_econocom.restaurant.domain.respository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRestaurantsUseCase(private val repository: RestaurantRepository) :
    BaseUseCase<GetRestaurantsUseCase.Params, Flow<ResponseStatus<List<Restaurant>>>> {
    data class Params(val latitude: Double, val longitude: Double)

    override suspend fun execute(param: Params): Flow<ResponseStatus<List<Restaurant>>> = flow {
        emit(ResponseStatus.Loading(true))
        val result = repository.getRestaurants(param.latitude, param.longitude)
        emit(result)
        emit(ResponseStatus.Loading(false))
    }
}