package com.example.studysiege.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.studysiege.service.TimerForegroundService

class NotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        when (intent.action) {

            "ACTION_PAUSE" -> {



            }

            "ACTION_STOP" -> {

                context.stopService(
                    Intent(
                        context,
                        TimerForegroundService::class.java
                    )
                )
            }
        }
    }
}