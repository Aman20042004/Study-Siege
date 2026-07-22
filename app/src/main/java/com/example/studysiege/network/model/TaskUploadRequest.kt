package com.example.studysiege.network.model





data class TaskUploadRequest(
    val studyId: String,
    val tasks: List<Task>
)