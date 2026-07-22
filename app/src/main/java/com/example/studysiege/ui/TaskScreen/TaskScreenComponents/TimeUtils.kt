package com.example.studysiege.ui.TaskScreen.TaskScreenComponents

fun formatDuration(seconds: Long): String {

    val hrs = seconds / 3600
    val mins = (seconds % 3600) / 60
    val secs = seconds % 60

    return String.format(
        "%02d:%02d:%02d",
        hrs,
        mins,
        secs
    )
}