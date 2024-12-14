package com.example.sensorreader.feature.home

import android.hardware.SensorEvent
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.sensorreader.data.model.SensorDataModel
import com.example.sensorreader.data.model.toModel
import com.example.sensorreader.domain.ConnectToAccelerometerSensorUseCase
import com.example.sensorreader.domain.ConnectToGyroscopeSensorUseCase
import com.example.sensorreader.domain.SetSensorDataToDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val connectToAccelerometerSensorUseCase: ConnectToAccelerometerSensorUseCase,
    private val connectToGyroscopeSensorUseCase: ConnectToGyroscopeSensorUseCase,
    private val setSensorDataToDatabaseUseCase: SetSensorDataToDatabaseUseCase,
) : ViewModel() {

    companion object {
        private const val SENSOR_DATA_DELAY = 150L
    }

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val state: StateFlow<HomeUiState>
        get() = _state.asStateFlow()

    private var sensorEventList: List<SensorDataModel> = emptyList()
    private var sensorDataJob: Job? = null

    fun onFetchButtonClick() = viewModelScope.launch {
        sensorDataJob = connectToSensors()
    }

    fun onStopReceivingDataClick() = viewModelScope.launch {
        disconnectSensors()
    }

    private fun connectToSensors() = viewModelScope.launch {
        connectToAccelerometerSensorUseCase.run().cancellable()
            .combine(connectToGyroscopeSensorUseCase.run().cancellable()) { t1, t2 ->
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
        checkSensorDataAmount()
        Log.wtf(
            this@HomeViewModel::class.simpleName,
            "amount of data lines: ${sensorEventList.size}, accelerometer x = ${accelerometer.x}, gyroscope x = ${gyroscope.x}"
        )
    }

    private fun checkSensorDataAmount() = viewModelScope.launch(Dispatchers.IO) {
        if (sensorEventList.size == 500) {
            setSensorDataToDatabaseUseCase.run(sensorEventList)
            sensorEventList = emptyList()
        }
    }

    private fun disconnectSensors() = viewModelScope.launch {
        sensorDataJob?.cancel()
        sensorDataJob = null
        Log.wtf(this@HomeViewModel::class.simpleName, "jobs cancelled")
        _state.value = HomeUiState.Idle
    }
}