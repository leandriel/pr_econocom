package com.leandroid.system.pr_econocom.restaurant.di

import com.leandroid.system.pr_econocom.restaurant.data.api.RestaurantService
import com.leandroid.system.pr_econocom.restaurant.data.repository.RestaurantRepositoryImpl
import com.leandroid.system.pr_econocom.restaurant.domain.respository.RestaurantRepository
import com.leandroid.system.pr_econocom.restaurant.domain.usecase.GetRestaurantDetailUseCase
import com.leandroid.system.pr_econocom.restaurant.domain.usecase.GetRestaurantsBySearchUseCase
import com.leandroid.system.pr_econocom.restaurant.domain.usecase.GetRestaurantsUseCase
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantModule = module {
    single {
        RestaurantService.build()
    }

    single<RestaurantRepository> {
        RestaurantRepositoryImpl(get())
    }

    single { GetRestaurantsUseCase(repository = get()) }
    single { GetRestaurantsBySearchUseCase(repository = get()) }
    single { GetRestaurantDetailUseCase(repository = get()) }

    viewModel {
        RestaurantViewModel(
            getRestaurants = get(),
            getRestaurantsBySearch = get(),
            getRestaurantDetail = get()
        )
    }
}