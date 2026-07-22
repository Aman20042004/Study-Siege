package com.example.studysiege.ui.Calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studysiege.ui.Calendar.CalendarComponents.CalendarCard
import com.example.studysiege.ui.Calendar.CalendarComponents.HeaderCalendar
import com.example.studysiege.ui.Calendar.CalendarComponents.MonthlyOverviewSection
import com.example.studysiege.ui.Calendar.CalendarComponents.SelectedDayCard
import com.example.studysiege.ui.Calendar.CalendarComponents.generateCalendarDays
import com.example.studysiegeui.ui.model.Screen
import java.util.Calendar

enum class FocusType { HIGH, AVERAGE, LOW, NONE }

data class Day(val date: Int, val type: FocusType)

//@Preview(showBackground = true)
@Composable
//fun CalendarScreen() {
fun CalendarScreen(navController: NavController) {

    var currentCalendar by remember {
        mutableStateOf(Calendar.getInstance())
    }

    val days =
        remember(currentCalendar.timeInMillis) {
            generateCalendarDays(currentCalendar)
        }
    var selectedDay by remember(days) {
        mutableStateOf(days.first())
    }

    Column(
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
            .padding(16.dp)
    ) {

        // 🔥 HEADER
        HeaderCalendar()

        Spacer(Modifier.height(5.dp))

        // 🔥 CALENDAR CARD
        CalendarCard(
            calendar = currentCalendar,
            days = days,
            selected = selectedDay,

            onPreviousMonth = {

                currentCalendar =
                    (currentCalendar.clone() as Calendar).apply {

                        add(Calendar.MONTH, -1)
                    }
            },

            onNextMonth = {

                currentCalendar =
                    (currentCalendar.clone() as Calendar).apply {

                        add(Calendar.MONTH, 1)
                    }
            }

        ) {

            selectedDay = it
        }

        Spacer(Modifier.height(5.dp))

        // 🔥 SELECTED DAY
        SelectedDayCard(
            day = selectedDay.date,
            onViewReport = {
                navController.navigate(
                    Screen.Analytics.createRoute(
                        selectedDay.timeInMillis
                    )
                )
            }
        )

        Spacer(Modifier.height(5.dp))

        // 🔥 MONTHLY OVERVIEw
        Box(modifier=Modifier.height(180.dp).fillMaxWidth()) {
            MonthlyOverviewSection()
        }
        Spacer(Modifier.height(2.dp))
       // BottomNavBar(navController )
       // BottomNavBar()

    }

}
