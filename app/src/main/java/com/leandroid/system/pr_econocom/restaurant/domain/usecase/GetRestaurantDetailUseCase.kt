package com.leandroid.system.pr_econocom.restaurant.domain.usecase

import com.leandroid.system.pr_econocom.core.data.ResponseStatus
import com.leandroid.system.pr_econocom.core.domain.BaseUseCase
import com.leandroid.system.pr_econocom.restaurant.domain.model.Restaurant
import com.leandroid.system.pr_econocom.restaurant.domain.respository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRestaurantDetailUseCase(private val repository: RestaurantRepository) :
    BaseUseCase<String, Flow<ResponseStatus<Restaurant?>>> {
    override suspend fun execute(param: String): Flow<ResponseStatus<Restaurant?>> = flow {
        emit(ResponseStatus.Loading(true))
        val result = repository.getRestaurantDetail(param)
        emit(result)
        emit(ResponseStatus.Loading(false))
    }
}