package com.example.studysiege.ui.Analytics

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studysiege.room.database.DatabaseProvider
import com.example.studysiege.ui.Analytics.AnalyticScreenComponents.AppHeader

import com.example.studysiege.ui.Analytics.AnalyticScreenComponents.FocusCard
import com.example.studysiege.ui.Analytics.AnalyticScreenComponents.FocusConsistencyCard
import com.example.studysiege.ui.Analytics.AnalyticScreenComponents.MostWastedCard


import com.example.studysiege.ui.Analytics.AnalyticScreenComponents.SiegeLevelCard
import com.example.studysiege.ui.Analytics.AnalyticScreenComponents.StatsRow
import com.example.studysiege.ui.Analytics.AnalyticScreenComponents.TopTasksCard
import com.example.studysiege.navigation.model.FocusConsistencyData
import com.example.studysiege.network.mapper.CloudRepository
import com.example.studysiege.repository.TaskRepository
import com.example.studysiege.viewModel.TaskViewModel
import com.example.studysiegeui.ui.model.Screen
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

//@Preview(showBackground = true)
@Composable
//fun AnalyticsScreen() {

fun AnalyticsScreen(
    navController: NavController,
    selectedDate: Long
) {

    val calendar = Calendar.getInstance().apply {
        timeInMillis = selectedDate
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val startOfDay = calendar.timeInMillis

    calendar.add(Calendar.DAY_OF_MONTH, 1)
    val endOfDay = calendar.timeInMillis - 1

    val filterStart = startOfDay
    val filterEnd = endOfDay

    val todayCalendar = Calendar.getInstance().apply {

        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val isToday =
        startOfDay == todayCalendar.timeInMillis

    val selectedDateText =
        SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        ).format(Date(selectedDate))



    val context = LocalContext.current

    val taskDao =
        DatabaseProvider
            .getDatabase(context)
            .taskDao()

    val cloudRepository = remember {
        CloudRepository(taskDao)
    }

    val taskRepository = remember {
        TaskRepository(
            taskDao,
            cloudRepository
        )
    }

    val taskViewModel = remember {
        TaskViewModel(
            taskDao,
            taskRepository
        )
    }

    val tasks =
        taskViewModel
            .getTasksForDate(
                filterStart,
                filterEnd
            )
            .collectAsState(initial = emptyList())

    val allTasks =
        tasks.value

    val dayStatus =
        taskViewModel.getDayStatus(allTasks)

    Log.d(
        "StudySiege",
        dayStatus.name
    )
    var consistencyData by remember {

        mutableStateOf(

            FocusConsistencyData(
                currentStreak = 0,
                bestStreak = 0,
                successRate = 0,
                greenDays = 0,
                yellowDays = 0,
                redDays = 0
            )
        )
    }

    LaunchedEffect(allTasks) {

        consistencyData =
            taskViewModel.getFocusConsistencyData()

    }

    val relevantSeconds =
        allTasks
            .filter { it.type == "Relevant" }
            .sumOf { it.elapsedSeconds }

    val gapSeconds =
        allTasks
            .filter { it.type == "Gap" }
            .sumOf { it.elapsedSeconds }

    val notCoreSeconds =
        allTasks
            .filter { it.type == "Not Core Task" }
            .sumOf { it.elapsedSeconds }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF05070D),
                        Color(0xFF0B0F1A),
                        Color(0xFF120F2A)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize().verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .padding(bottom = 88.dp)
        ) {

            Spacer(Modifier.height(10.dp))
            // 🔥 HEADER
            AppHeader(
                title = "Analytics",
                subtitle = selectedDateText,
                showMenu = false,
                showNotification = false
            )



            // 🔥 FOCUS CARD
            Box(
                modifier = Modifier.height(237.dp)
            ) {
                FocusCard(
                    relevantSeconds = relevantSeconds,
                    dailyGoalHours = 8
                )
            }

            Spacer(Modifier.height(8.dp))

            // 🔥 STATS ROW
            Box(
                modifier = Modifier.height(70.dp)
            ) {
                StatsRow(
                    relevantSeconds = relevantSeconds,
                    gapSeconds = gapSeconds,
                    notCoreSeconds = notCoreSeconds
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                FocusConsistencyCard(
                    modifier = Modifier.fillMaxWidth(),
                    data = consistencyData
                )

                Spacer(Modifier.height(9.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    TopTasksCard(
                        modifier = Modifier.weight(1f),
                        tasks = allTasks
                    )

                    MostWastedCard(
                        modifier = Modifier.weight(1f),
                        tasks = allTasks
                    )
                }
            }

        }
    }
}
