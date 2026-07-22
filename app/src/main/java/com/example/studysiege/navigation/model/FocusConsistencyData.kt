package com.example.studysiege.navigation.model

data class FocusConsistencyData(

    val currentStreak: Int,

    val bestStreak: Int,

    val successRate: Int,

    val greenDays: Int,

    val yellowDays: Int,

    val redDays: Int

)