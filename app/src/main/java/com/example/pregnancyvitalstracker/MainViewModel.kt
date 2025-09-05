package com.example.pregnancyvitalstracker

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val _time = MutableStateFlow("00:00:00")
    val time: StateFlow<String> = _time.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    fun onTimeUpdate(newTime: String) {
        _time.value = newTime
    }

    fun onRunningStateChange(running: Boolean) {
        _isRunning.value = running
    }
}
