package com.example.sensorreader.feature.home

import com.example.sensorreader.data.model.SensorDataModel

sealed class HomeUiState {
    data object Idle : HomeUiState()
    data class Content(
        val accelerometerData: SensorDataModel,
        val gyroscopeData: SensorDataModel,
    ) : HomeUiState()

    fun isIdle(): Boolean = this is Idle
}