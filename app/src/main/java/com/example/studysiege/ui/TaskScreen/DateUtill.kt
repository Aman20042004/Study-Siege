package com.example.studysiege.ui.TaskScreen

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getHistoryHeader(time: Long): String {

    val today = Calendar.getInstance()

    val taskDay = Calendar.getInstance().apply {
        timeInMillis = time
    }

    return when {

        today.get(Calendar.YEAR) == taskDay.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == taskDay.get(Calendar.DAY_OF_YEAR) -> {

            "Today"
        }

        today.apply { add(Calendar.DAY_OF_YEAR, -1) }
            .get(Calendar.DAY_OF_YEAR) ==
                taskDay.get(Calendar.DAY_OF_YEAR) &&
                today.get(Calendar.YEAR) ==
                taskDay.get(Calendar.YEAR) -> {

            "Yesterday"
        }

        else -> {

            SimpleDateFormat(
                "dd MMMM yyyy",
                Locale.getDefault()
            ).format(Date(time))
        }
    }
}