package com.example.pregnancyvitalstracker.home

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pregnancyvitalstracker.time_service.TimeService
import com.example.submissiondemo.dataStore.VitalsDio
import com.example.submissiondemo.dataStore.VitalsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val vitalsDio: VitalsDio,
): ViewModel() {
    val isShowDialog =  mutableStateOf(false)
    val firstText =  mutableStateOf("")
    val secondText = mutableStateOf("")
    val babyKickValue =  mutableStateOf("")
    val weightValue = mutableStateOf("")

    private val _vitalsList = MutableStateFlow<List<VitalsEntity>>(emptyList())
    val vitalsList: StateFlow<List<VitalsEntity>> = _vitalsList.asStateFlow()

    init {
        loadVitals()
    }
    fun loadVitals() {
        viewModelScope.launch {
            _vitalsList.value = vitalsDio.getAllVitals()
        }
    }

    fun addVitals(vitals: VitalsEntity, onSuccess: () -> Unit) {
        viewModelScope.launch {
            vitalsDio.insertVitals(vitals)
            loadVitals()
            onSuccess()
        }
    }
}