package com.example.eldercare

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.eldercare.model.service.notifications.createNotificationChannels
import com.example.eldercare.common.workmanager.setupRepeatingWork
import dagger.hilt.android.AndroidEntryPoint

/**
 * Entry point for the entire app.
 */
@AndroidEntryPoint
class ElderCareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test", "before createNotificationChannels")
        createNotificationChannels(this)
        Log.d("test", "after createNotificationChannels")

        setupRepeatingWork(this)
        setContent { ElderCareApp() }
    }
}

