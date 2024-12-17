package com.example.sensorreader.data.model

import android.hardware.SensorEvent
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class SensorDataModel(
    @PrimaryKey val id: String,
    val timestamp: Long,
    val type: String,
    val x: Float,
    val y: Float,
    val z: Float,
)

fun SensorEvent.toModel(type: String): SensorDataModel {
    val id = UUID.randomUUID().toString()
    return SensorDataModel(id, timestamp, type, x = values[0], y = values[1], z = values[2])
}
