package com.example.studysiege.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.studysiege.MainActivity
import com.example.studysiege.R
import com.example.studysiege.room.database.DatabaseProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull

class TimerForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private var currentTitle = "Study Session"
    private var currentTaskId = -1
    private var baseElapsedSeconds = 0L
    private var lastResumeTime= 0L
    private var targetDuration = 0L
    private var isServicePaused = false
    private var mediaPlayer: android.media.MediaPlayer? = null
    private var alarmPlaying = false

    private fun startTimer() {
        timerJob?.cancel()

        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)

                val displaySeconds = baseElapsedSeconds +
                        (System.currentTimeMillis() - lastResumeTime) / 1000

                // Target complete hone par sirf ek baar alarm bajao
                if (
                    targetDuration > 0 &&
                    displaySeconds >= targetDuration &&
                    !alarmPlaying
                ) {
                    alarmPlaying = true
                    playCompletionTune()
                }

                updateNotification()
            }
        }
    }


    private fun playCompletionTune() {
        if (mediaPlayer == null) {
            val alarmUri = android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_ALARM)
                ?: android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_NOTIFICATION)

            mediaPlayer = android.media.MediaPlayer.create(this, alarmUri).apply {
                isLooping = false // Ek baar bajane ke liye
                start()
            }
        }
    }

    private fun formatTime(seconds: Long): String {

        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60

        return String.format(
            "%02d:%02d:%02d",
            hours,
            minutes,
            secs
        )
    }

    private fun updateNotification() {

        NotificationManagerCompat
            .from(this)
            .notify(
                1001,
                buildNotification()
            )
    }

    private var timerJob: Job? = null

    private var elapsedSeconds = 0L


    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        Log.d("StudySiege", "TimerForegroundService started")

        val action = intent?.action

        // 🔥 ROUTE 1: Agar Notification se PAUSE ya RESUME click hua hai
        if (action == ACTION_PAUSE || action == ACTION_RESUME) {


            val taskDao = DatabaseProvider.getDatabase(applicationContext).taskDao()

            CoroutineScope(Dispatchers.IO).launch {


                val task = taskDao.getTaskById(currentTaskId).firstOrNull()

                task?.let {
                    if (action == ACTION_PAUSE) {
                        stopCompletionTune()
                        timerJob?.cancel()
                        isServicePaused = true


                        baseElapsedSeconds += (System.currentTimeMillis() - lastResumeTime) / 1000



                        taskDao.updateTask(it.copy(status = "Paused", elapsedSeconds = baseElapsedSeconds))

                        withContext(Dispatchers.Main) { updateNotification() }

                    } else if (action == ACTION_RESUME) {
                        isServicePaused = false
                        lastResumeTime = System.currentTimeMillis()

                        // DB Update: Status Running
                        taskDao.updateTask(it.copy(status = "Running", lastResumeTime = lastResumeTime))

                        withContext(Dispatchers.Main) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                                startForeground(
                                    1001,
                                    buildNotification(),
                                    ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
                                )
                            } else {
                                startForeground(1001, buildNotification())
                            }

                            startTimer()
                            updateNotification()
                        }
                    }
                }
            }
            return START_STICKY
        }


        if (action == "UPDATE") {
            currentTaskId = intent.getIntExtra(EXTRA_TASK_ID, currentTaskId)
            currentTitle = intent.getStringExtra(EXTRA_TITLE) ?: currentTitle
            baseElapsedSeconds = intent.getLongExtra(EXTRA_ELAPSED_SECONDS, baseElapsedSeconds)
            lastResumeTime = intent.getLongExtra(EXTRA_LAST_RESUME_TIME, lastResumeTime)


            targetDuration = intent.getLongExtra(EXTRA_TARGET_DURATION, targetDuration)


            if (isServicePaused) {
                isServicePaused = false
                startTimer()
            }

            return START_STICKY
        }


        currentTaskId = intent?.getIntExtra(EXTRA_TASK_ID, -1) ?: -1
        currentTitle = intent?.getStringExtra(EXTRA_TITLE) ?: "Study Session"
        baseElapsedSeconds = intent?.getLongExtra(EXTRA_ELAPSED_SECONDS, 0L) ?: 0L
        lastResumeTime = intent?.getLongExtra(EXTRA_LAST_RESUME_TIME, 0L) ?: 0L



        targetDuration = intent?.getLongExtra(EXTRA_TARGET_DURATION, 0L) ?: 0L

        isServicePaused = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                1001,
                buildNotification(),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
            )
        } else {
            startForeground(1001, buildNotification())
        }
        startTimer()

        return START_STICKY
    }

    override fun onDestroy() {
        timerJob?.cancel()

        stopCompletionTune()

        super.onDestroy()
    }

    private fun buildNotification(): Notification {
        val displaySeconds = if (isServicePaused) {
            baseElapsedSeconds
        } else {
            baseElapsedSeconds + (System.currentTimeMillis() - lastResumeTime) / 1000
        }


        val openIntent = Intent(Intent.ACTION_VIEW, Uri.parse("studysiege://pause/$currentTaskId"), this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(this, currentTaskId, openIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Notification Builder
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(currentTitle)
            .setContentText(formatTime(displaySeconds))
            .setOnlyAlertOnce(true)
            .setSilent(true)
            .setOngoing(true)
            .setContentIntent(pendingIntent)

        // Dynamic Action Button Logic
        if (isServicePaused) {
            // Agar pause hai, toh RESUME (>) button dikhao
            val resumeIntent = Intent(this, TimerForegroundService::class.java).apply { action = ACTION_RESUME }
            val resumePendingIntent = PendingIntent.getService(this, 102, resumeIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            builder.addAction(android.R.drawable.ic_media_play, "Resume", resumePendingIntent)

        } else {

            val pauseIntent = Intent(this, TimerForegroundService::class.java).apply { action = ACTION_PAUSE }
            val pausePendingIntent = PendingIntent.getService(this, 103, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            builder.addAction(android.R.drawable.ic_media_pause, "Pause", pausePendingIntent)
        }

        return builder.build()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Study Timer",
                NotificationManager.IMPORTANCE_LOW
            )

            val manager =
                getSystemService(
                    NotificationManager::class.java
                )

            manager.createNotificationChannel(channel)
        }
    }

    private fun stopCompletionTune() {
        mediaPlayer?.let {
            try {
                if (it.isPlaying) it.stop()
            } catch (_: Exception) {
            } finally {
                it.release()
            }
        }
        mediaPlayer = null
        alarmPlaying = false
    }

    companion object {

        const val CHANNEL_ID = "study_timer_channel"
        const val EXTRA_ELAPSED_SECONDS = "elapsed_seconds"
        const val EXTRA_TASK_ID = "task_id"
        const val EXTRA_LAST_RESUME_TIME = "last_resume_time"
        const val EXTRA_TITLE = "title"
        const val EXTRA_TYPE = "type"
        const val EXTRA_MODE = "mode"
        const val ACTION_PAUSE = "com.example.studysiege.PAUSE"
        const val ACTION_RESUME = "com.example.studysiege.RESUME"
        const val EXTRA_TARGET_DURATION = "target_duration"
    }
}