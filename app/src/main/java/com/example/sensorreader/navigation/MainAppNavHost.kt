package com.example.sensorreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.sensorreader.main.MainAppState

@Composable
fun MainAppNavHost(
    appState: MainAppState,
    startDestination: String = HOME_ROUTE,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen()
    }
}
