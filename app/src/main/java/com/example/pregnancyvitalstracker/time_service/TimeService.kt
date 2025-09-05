package com.example.pregnancyvitalstracker.time_service

import android.app.*
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.util.*

class TimeService : Service() {
    companion object {
        const val CHANNEL_ID = "time_service_channel"
        const val NOTIFICATION_ID = 12345
        const val ACTION_START = "com.example.timerfgservice.ACTION_START"
        const val ACTION_STOP  = "com.example.timerfgservice.ACTION_STOP"
        const val ACTION_TOGGLE = "com.example.timerfgservice.ACTION_TOGGLE"
    }

    inner class LocalBinder : Binder() {
        fun getService(): TimeService = this@TimeService
    }
    private val binder = LocalBinder()

    private val _timeFlow = MutableSharedFlow<String>(replay = 1)
    val timeFlow: SharedFlow<String> = _timeFlow.asSharedFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunningFlow: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var timerJob: Job? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startForegroundAndTimer()
            ACTION_STOP  -> stopForegroundAndSelf()
            ACTION_TOGGLE -> if (_isRunning.value) stopForegroundAndSelf() else startForegroundAndTimer()
            else -> { /* no-op */ }
        }
        // Restart if killed by system
        return START_STICKY
    }

    private fun startForegroundAndTimer() {
        if (_isRunning.value) return

        // Start as foreground
        startForeground(NOTIFICATION_ID, buildNotification())

        _isRunning.value = true
        timerJob = serviceScope.launch {
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            while (isActive) {
                val now = sdf.format(Date())
                // emit time
                _timeFlow.emit(now)
                delay(1000L)
            }
        }
    }

    private fun stopForegroundAndSelf() {
        // stop timer and remove foreground
        timerJob?.cancel()
        timerJob = null
        _isRunning.value = false
        try {
            stopForeground(true)
            stopSelf()
        } catch (t: Throwable) {
            // ignore
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timerJob?.cancel()
        serviceScope.cancel()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        val restartIntent = Intent(applicationContext, TimeService::class.java).apply {
            action = ACTION_START
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            applicationContext.startForegroundService(restartIntent)
        } else {
            applicationContext.startService(restartIntent)
        }
        super.onTaskRemoved(rootIntent)
    }

    private fun buildNotification(): Notification {
        val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
        val pendingLaunch = PendingIntent.getActivity(
            this,
            0,
            launchIntent,
            pendingFlags()
        )

        val stopIntent = Intent(this, TimeService::class.java).apply { action = ACTION_STOP }
        val pendingStop = PendingIntent.getService(this, 1, stopIntent, pendingFlags())

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Timer Running")
            .setContentText("Emitting time every second")
//            .setSmallIcon(R.drawable.ic_notification)
            .setContentIntent(pendingLaunch)
            .setOngoing(true)
            .addAction(0, "Stop", pendingStop)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(NotificationManager::class.java)
            val channel = NotificationChannel(CHANNEL_ID, "Time Service", NotificationManager.IMPORTANCE_LOW)
            channel.setSound(null, null)
            nm.createNotificationChannel(channel)
        }
    }

    private fun pendingFlags(): Int {
        var flags = PendingIntent.FLAG_UPDATE_CURRENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags = flags or PendingIntent.FLAG_IMMUTABLE
        }
        return flags
    }
}








