package com.example.sensorreader.feature.home

import android.hardware.SensorEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensorreader.data.model.SensorDataModel
import com.example.sensorreader.data.model.toModel
import com.example.sensorreader.domain.ConnectToAccelerometerSensorUseCase
import com.example.sensorreader.domain.ConnectToGyroscopeSensorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val connectToAccelerometerSensorUseCase: ConnectToAccelerometerSensorUseCase,
    private val connectToGyroscopeSensorUseCase: ConnectToGyroscopeSensorUseCase,
) : ViewModel() {

    companion object {
        private const val SENSOR_DATA_DELAY = 150L
    }

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val state: StateFlow<HomeUiState>
        get() = _state.asStateFlow()

    private var sensorEventList: List<SensorDataModel> = emptyList()
    private var sensorDataJob: Job? = null
    private var accelerometerFlow: Flow<SensorEvent>? = null
    private var gyroscopeFlow: Flow<SensorEvent>? = null

    fun onFetchButtonClick(isIdleState: Boolean) = viewModelScope.launch {
        if (isIdleState) {
            connectToSensorsJob()
        } else {
            disconnectSensors()
        }
    }

    private fun connectToSensorsJob() {
        sensorDataJob = connectToSensors()
    }

    private fun connectToSensors() = viewModelScope.launch {
        connectToAccelerometerSensorUseCase.run()
            .combine(connectToGyroscopeSensorUseCase.run()) { t1, t2 ->
                val accelerometerModel = t1.toModel("accelerometer")
                val gyroscopeModel = t2.toModel("gyroscope")
                accelerometerModel to gyroscopeModel
            }.collect { (accelerometer, gyroscope) ->
                handleDataFetchingResult(accelerometer, gyroscope)
                delay(SENSOR_DATA_DELAY)
            }
    }

    private fun handleDataFetchingResult(
        accelerometer: SensorDataModel,
        gyroscope: SensorDataModel
    ) {
        sensorEventList += accelerometer
        sensorEventList += gyroscope
        _state.value = HomeUiState.Content(accelerometer, gyroscope)
        Log.wtf(
            this@HomeViewModel::class.simpleName,
            "amount of data lines: ${sensorEventList.size}"
        )
    }

    private fun disconnectSensors() = viewModelScope.launch {
        sensorDataJob?.cancel()
        sensorDataJob = null
        accelerometerFlow = null
        gyroscopeFlow = null
        Log.wtf(this@HomeViewModel::class.simpleName, "jobs cancelled")
        _state.value = HomeUiState.Idle
    }
}