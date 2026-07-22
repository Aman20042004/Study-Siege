package com.example.studysiegeui.ui.model

import android.net.Uri

sealed class Screen(val route: String) {

    object Welcome : Screen("welcome")

    object Home : Screen("home")

    object Analytics : Screen("analytics/{date}") {

        fun createRoute(date: Long): String {
            return "analytics/$date"
        }
    }

    object Calendar : Screen("calendar")

    object Profile : Screen("profile")

    object StartTask : Screen("start_task")

    object MostUsedTasks : Screen("most_used_tasks/{date}") {

        fun createRoute(date: Long): String {
            return "most_used_tasks/$date"
        }
    }


  //  object Pause : Screen("pause")
  object Pause : Screen("pause/{taskId}") {

      fun createRoute(taskId: Int): String {
          return "pause/$taskId"
      }
  }
}