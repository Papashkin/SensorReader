package com.example.sensorreader.feature.home

sealed class HomeUiState {
    data object Idle : HomeUiState()
    data class Content(
        val accelerometerData: String = "",
        val gyroscopeData: String = "",
    ) : HomeUiState()


    fun isIdle(): Boolean = this is Idle
}