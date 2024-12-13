package com.example.sensorreader.data

import android.hardware.SensorEventListener
import android.hardware.SensorManager
import javax.inject.Inject

class SensorDataProviderImpl @Inject constructor(
    private val sensorManager: SensorManager
): SensorDataProvider {

    override fun startCollecting(sensorType: Int, listener: SensorEventListener) {
        val sensor = sensorManager.getDefaultSensor(sensorType)
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun stopCollecting(sensorType: Int, listener: SensorEventListener) {
        val sensor = sensorManager.getDefaultSensor(sensorType)
        sensorManager.unregisterListener(listener, sensor)
    }
}