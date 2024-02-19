package com.leandroid.system.pr_econocom.ui.theme.ui.navigation

sealed class Screen(
    val route: String
) {

    object Home : Screen(
        route = "home"
    )
    object Detail : Screen(
        route = "detail/{locationId}"
    )
}