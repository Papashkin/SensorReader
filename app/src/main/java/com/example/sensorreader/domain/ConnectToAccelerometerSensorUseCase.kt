package com.example.sensorreader.domain

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.example.sensorreader.data.repository.SensorRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ConnectToAccelerometerSensorUseCase @Inject constructor(
    private val repository: SensorRepository
) {

    fun run(): Flow<SensorEvent> = callbackFlow {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let { trySend(it) }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // no-op
            }
        }

        repository.startCollectingSensorData(Sensor.TYPE_ACCELEROMETER, listener)

        awaitClose {
            repository.stopCollectingSensorData(Sensor.TYPE_ACCELEROMETER, listener)
        }
    }
}