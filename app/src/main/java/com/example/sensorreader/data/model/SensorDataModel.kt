package com.example.sensorreader.data.model

import android.hardware.SensorEvent
import java.util.UUID

data class SensorDataModel(
    val id: String,
    val timestamp: Long,
    val type: String,
    val x: Float,
    val y: Float,
    val z: Float,
)

fun SensorEvent.toModel(type: String): SensorDataModel {
    val timestamp = System.currentTimeMillis()
    val id = UUID.randomUUID().toString()
    return SensorDataModel(id, timestamp, type, x = values[0], y = values[1], z = values[2])
}
