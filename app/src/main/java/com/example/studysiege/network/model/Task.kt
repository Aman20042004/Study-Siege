package com.example.studysiege.network.model



data class Task(
    val id: Int,
    val title: String,
    val type: String,
    val status: String,
    val mode: String,
    val elapsedSeconds: Long,
    val targetDuration: Long,
    val createdAt: Long,
    val completedAt: Long,
    val lastResumeTime: Long,
    val isSynced: Boolean
)