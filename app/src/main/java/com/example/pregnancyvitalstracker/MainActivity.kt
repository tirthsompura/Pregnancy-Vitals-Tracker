package com.example.pregnancyvitalstracker

import HomePage
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.pregnancyvitalstracker.ui.theme.PregnancyVitalsTrackerTheme
import com.example.pregnancyvitalstracker.time_service.TimeService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    private var serviceBound = false
    private var timeService: TimeService? = null

    private val collectorJobs = mutableListOf<Job>()

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            val localBinder = binder as? TimeService.LocalBinder ?: return
            val svc = localBinder.getService()
            timeService = svc
            serviceBound = true

            collectorJobs.forEach { it.cancel() }
            collectorJobs.clear()

            val lifecycleOwner = this@MainActivity
            val scope = lifecycleOwner.lifecycleScope

            collectorJobs += scope.launch {
                svc.timeFlow.collectLatest { latest ->
                    viewModel.onTimeUpdate(latest)
                }
            }
            collectorJobs += scope.launch {
                svc.isRunningFlow.collectLatest { running ->
                    viewModel.onRunningStateChange(running)
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceBound = false
            timeService = null

            collectorJobs.forEach { it.cancel() }
            collectorJobs.clear()
            viewModel.onRunningStateChange(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PregnancyVitalsTrackerTheme {
                val time by viewModel.time.collectAsState()
                val isRunning by viewModel.isRunning.collectAsState()
                HomePage(
                    isRunning = isRunning,
                    currentTime = time,
                    onTimerStart = {
                        val ctx = this@MainActivity
                        if (!isRunning) {
                            // Start foreground service
                            val startIntent = Intent(ctx, TimeService::class.java).apply { action = TimeService.ACTION_START }
                            ContextCompat.startForegroundService(ctx, startIntent)

                            // Bind to receive updates
                            bindService(Intent(ctx, TimeService::class.java), connection, Context.BIND_AUTO_CREATE)
                        } else {
                            // service stop
                            val stopIntent = Intent(ctx, TimeService::class.java).apply { action = TimeService.ACTION_STOP }
                            ctx.startService(stopIntent)
                        }
                    }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, TimeService::class.java)
        bindService(intent, connection, 0)
    }

    override fun onStop() {
        super.onStop()
        if (serviceBound) {
            try {
                unbindService(connection)
            } catch (t: Throwable) {}
        }
        serviceBound = false
        timeService = null
        collectorJobs.forEach { it.cancel() }
        collectorJobs.clear()
    }
}
