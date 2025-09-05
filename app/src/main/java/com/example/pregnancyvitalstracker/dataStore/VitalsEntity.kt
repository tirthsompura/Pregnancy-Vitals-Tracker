package com.example.submissiondemo.dataStore
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vitals")
data class VitalsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sysBP: String,
    val diaBP: String,
    val weight: String,
    val kicks: String,
    val timestamp: Long = System.currentTimeMillis()
)