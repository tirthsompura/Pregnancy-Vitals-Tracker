package com.example.pregnancyvitalstracker.dataclass

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.submissiondemo.dataStore.VitalsDio
import com.example.submissiondemo.dataStore.VitalsEntity

@Database(entities = [VitalsEntity::class], version = 1)
abstract class VitalsDatabase : RoomDatabase() {
    abstract fun vitalsDao(): VitalsDio
}