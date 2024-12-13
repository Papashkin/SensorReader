package com.example.sensorreader.main

import androidx.compose.runtime.Composable
import com.example.sensorreader.navigation.MainAppNavHost

@Composable
fun MainApp(
    appState: MainAppState = rememberMainAppState(),
) {
    MainAppNavHost(
        appState = appState,
    )
}
