package com.example.sensorreader.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMainAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
):MainAppState{
    return remember(
        navController,
        coroutineScope,
    ) {
        MainAppState(
            navController,
            coroutineScope,
        )
    }
}


@Stable
class MainAppState(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {
}
