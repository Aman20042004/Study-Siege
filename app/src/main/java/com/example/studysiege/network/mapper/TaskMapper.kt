package com.example.studysiege.network.mapper



import com.example.studysiege.network.model.Task
import com.example.studysiege.room.entity.TaskEntity

fun TaskEntity.toNetworkTask(): Task {
    return Task(
        id = id,
        title = title,
        type = type,
        status = status,
        mode = mode,
        elapsedSeconds = elapsedSeconds,
        targetDuration = targetDuration,
        createdAt = createdAt,
        completedAt = completedAt,
        lastResumeTime = lastResumeTime,
        isSynced = isSynced
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        type = type,
        status = status,
        mode = mode,
        elapsedSeconds = elapsedSeconds,
        targetDuration = targetDuration,
        createdAt = createdAt,
        completedAt = completedAt,
        lastResumeTime = lastResumeTime,
        isSynced = isSynced
    )
}