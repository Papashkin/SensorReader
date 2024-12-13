package com.example.sensorreader.data.local.sensordataprovider

import android.hardware.SensorEventListener

interface SensorDataProvider {
    fun startCollecting(sensorType: Int, listener: SensorEventListener)
    fun stopCollecting(sensorType: Int, listener: SensorEventListener)
}