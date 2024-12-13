package com.example.sensorreader.data.local.sensordatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sensorreader.data.model.SensorDataModel

@Database(entities = [SensorDataModel::class], version = 1)
abstract class SensorsDatabase : RoomDatabase() {
    abstract fun sensorDataDao(): SensorDataDao
}