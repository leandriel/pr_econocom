package com.leandroid.system.pr_econocom.ui.theme.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.leandroid.system.pr_econocom.home.ui.screen.HomeScreen
import com.leandroid.system.pr_econocom.restaurant.ui.screen.DetailScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,


        ) {
        composable(Screen.Home.route) {
            HomeScreen(goToDetail = { locationId ->
                navController.navigate(Screen.Detail.route.replace("{locationId}", locationId))
            })
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument("locationId") {
                type = NavType.StringType
            })
        ) {
            val locationId = it.arguments?.getString("locationId") ?: ""
            DetailScreen(
                locationId = locationId, onBack = { navController.popBackStack() }
            )
        }
    }
}