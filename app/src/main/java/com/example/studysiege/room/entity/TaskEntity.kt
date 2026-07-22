package com.example.studysiege.room.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val type: String, // Relevant, Gap, Not Core

    val status: String, // Running, Paused, Completed

    val mode: String, // Stopwatch, Timer

    val elapsedSeconds: Long = 0,

    val targetDuration: Long = 0,

    val createdAt: Long = System.currentTimeMillis(),

    val completedAt: Long = 0L,

    val lastResumeTime: Long = 0,

    val isSynced: Boolean = false
)
