package com.example.studysiege.ui.Calendar.CalendarComponents


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import com.example.studysiege.navigation.model.DayUI
import java.util.Calendar

@Composable
fun CalendarCard(
    calendar: Calendar,
    days: List<DayUI>,
    selected: DayUI,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onSelect: (DayUI) -> Unit
) {

    Column(
        Modifier
            .fillMaxWidth().height(410.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF0B0F1A))
            .border(
                1.dp,
                Brush.horizontalGradient(
                    listOf(Color(0xFF8A5CFF), Color(0xFF00D4FF))
                ),
                RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {

        // Month header
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Default.ArrowBack,
                null,
                tint = Color.White,
                modifier = Modifier.clickable {
                    onPreviousMonth()
                }
            )
            val formatter =
                remember {
                    java.text.SimpleDateFormat(
                        "MMMM yyyy",
                        java.util.Locale.getDefault()
                    )
                }

            Text(
                formatter.format(calendar.time),
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                Icons.Default.ArrowForward,
                null,
                tint = Color.White,
                modifier = Modifier.clickable {
                    onNextMonth()
                }
            )
        }

        Spacer(Modifier.height(12.dp))

        val week = listOf("SUN","MON","TUE","WED","THU","FRI","SAT")

        Row {
            week.forEach {
                Text(
                    it,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 10.sp
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.height(260.dp)
        ) {
            items(days) { day ->
                DayItemUI(day, selected, onSelect)
            }
        }

        Spacer(Modifier.height(8.dp))

        LegendCard()
    }
}