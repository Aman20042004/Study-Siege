package com.example.studysiege.notification

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.studysiege.service.TimerForegroundService

object NotificationHelper {

    fun startTimerService(
        context: Context,
        taskId: Int,
        title: String,
        type: String,
        mode: String,
        elapsedSeconds: Long,
        lastResumeTime: Long,
        targetDuration: Long
    ) {
        val intent = Intent(context, TimerForegroundService::class.java).apply {
            putExtra(TimerForegroundService.EXTRA_TASK_ID, taskId)
            putExtra(TimerForegroundService.EXTRA_TITLE, title)
            putExtra(TimerForegroundService.EXTRA_TYPE, type)
            putExtra(TimerForegroundService.EXTRA_MODE, mode)
            putExtra(TimerForegroundService.EXTRA_ELAPSED_SECONDS, elapsedSeconds)
            putExtra(TimerForegroundService.EXTRA_LAST_RESUME_TIME, lastResumeTime)
            putExtra(TimerForegroundService.EXTRA_TARGET_DURATION, targetDuration)
        }
        ContextCompat.startForegroundService(context, intent)
    }

    fun updateTimerService(
        context: Context,
        taskId: Int,
        title: String,
        type: String,
        mode: String,
        elapsedSeconds: Long,
        lastResumeTime: Long,
        targetDuration: Long
    ) {
        val intent = Intent(context, TimerForegroundService::class.java).apply {
            action = "UPDATE"
            putExtra(TimerForegroundService.EXTRA_TASK_ID, taskId)
            putExtra(TimerForegroundService.EXTRA_TITLE, title)
            putExtra(TimerForegroundService.EXTRA_TYPE, type)
            putExtra(TimerForegroundService.EXTRA_MODE, mode)
            putExtra(TimerForegroundService.EXTRA_ELAPSED_SECONDS, elapsedSeconds)
            putExtra(TimerForegroundService.EXTRA_LAST_RESUME_TIME, lastResumeTime)
            putExtra(TimerForegroundService.EXTRA_TARGET_DURATION, targetDuration)
        }
        context.startService(intent)
    }

    fun stopTimerService(context: Context) {
        context.stopService(Intent(context, TimerForegroundService::class.java))
    }
}

