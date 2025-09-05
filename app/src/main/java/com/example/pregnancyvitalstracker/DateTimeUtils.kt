package com.example.pregnancyvitalstracker

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object DateTimeUtils {

    fun Long.toDateTimeString(): String {
        val formatter = SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.getDefault())
        return formatter.format(Date(this))
    }

    fun formatTime(seconds: Int): String {
        val h = seconds / 3600
        val m = (seconds % 3600) / 60
        val s = seconds % 60
        return String.format("%02d:%02d:%02d", h, m, s)
    }
}