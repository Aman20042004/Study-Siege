package com.example.studysiege.navigation.model

import com.example.studysiege.ui.Calendar.FocusType

data class DayUI(
    val date: Int,
    val timeInMillis: Long,
    val type: FocusType,
    val isCurrentMonth: Boolean
)