package com.example.sensorreader.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sensorreader.feature.home.HomeScreenRoute

const val HOME_ROUTE = "home_route"

fun NavGraphBuilder.homeScreen() {
    composable(route = HOME_ROUTE) {
        HomeScreenRoute()
    }
}