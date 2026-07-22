package com.example.studysiege.ui.Calendar.CalendarComponents

import com.example.studysiege.navigation.model.DayUI
import com.example.studysiege.ui.Calendar.FocusType
import java.util.Calendar

fun generateCalendarDays(
    calendar: Calendar
): List<DayUI> {

    val days = mutableListOf<DayUI>()

    val cal = calendar.clone() as Calendar

    cal.set(Calendar.DAY_OF_MONTH, 1)
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)

    val firstDayOfWeek =
        cal.get(Calendar.DAY_OF_WEEK) - 1

    val daysInMonth =
        cal.getActualMaximum(Calendar.DAY_OF_MONTH)

    // Previous Month

    val previousMonth =
        cal.clone() as Calendar

    previousMonth.add(Calendar.MONTH, -1)

    val previousMonthDays =
        previousMonth.getActualMaximum(Calendar.DAY_OF_MONTH)

    for (i in firstDayOfWeek downTo 1) {

        previousMonth.set(
            Calendar.DAY_OF_MONTH,
            previousMonthDays - i + 1
        )

        days.add(
            DayUI(
                date = previousMonth.get(Calendar.DAY_OF_MONTH),
                timeInMillis = previousMonth.timeInMillis,
                type = FocusType.NONE,
                isCurrentMonth = false
            )
        )
    }

    // Current Month

    for (day in 1..daysInMonth) {

        cal.set(Calendar.DAY_OF_MONTH, day)

        days.add(
            DayUI(
                date = day,
                timeInMillis = cal.timeInMillis,

                type =
                    when (day % 4) {

                        0 -> FocusType.HIGH

                        1 -> FocusType.AVERAGE

                        2 -> FocusType.LOW

                        else -> FocusType.NONE
                    },

                isCurrentMonth = true
            )
        )
    }

    // Next Month

    val nextMonth =
        cal.clone() as Calendar

    nextMonth.add(Calendar.MONTH, 1)

    var nextDay = 1

    while (days.size < 42) {

        nextMonth.set(
            Calendar.DAY_OF_MONTH,
            nextDay
        )

        days.add(
            DayUI(
                date = nextDay,
                timeInMillis = nextMonth.timeInMillis,
                type = FocusType.NONE,
                isCurrentMonth = false
            )
        )

        nextDay++
    }

    return days
}