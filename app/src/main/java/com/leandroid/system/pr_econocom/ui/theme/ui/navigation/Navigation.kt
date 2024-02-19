package com.leandroid.system.pr_econocom.ui.theme.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.leandroid.system.pr_econocom.home.ui.screen.HomeScreen
import com.leandroid.system.pr_econocom.restaurant.ui.presentation.RestaurantState
import com.leandroid.system.pr_econocom.restaurant.ui.screen.DetailScreen
import com.leandroid.system.pr_econocom.restaurant.ui.screen.RestaurantScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,


    ) {
        composable(Screen.Home.route) {
            HomeScreen(goToDetail = {navController.navigate(Screen.Detail.route)})
        }
        composable(Screen.Detail.route) {
            DetailScreen(onBack = {navController.popBackStack()})
        }
        composable(route = Screen.Detail.route,
            arguments = listOf(navArgument("locationId") {
                type = NavType.IntType
            })) {
            val locationId = it.arguments?.getString("locationId") ?: ""
            HomeScreen(goToDetail = {navController.navigate(Screen.Detail.route)}, locationId = locationId)
        }
    }
}