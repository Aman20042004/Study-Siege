package com.example.studysiege.ui.startTaskScreen.start_task_screen_components

import androidx.compose.ui.tooling.preview.Preview
// Core Compose
import androidx.compose.runtime.Composable
// Layout
import androidx.compose.foundation.layout.*
// UI
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Graphics
import androidx.compose.ui.graphics.Color
// Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
// Background, Border, Clip
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
// Text & Material
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
// Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.TaskTypeSection


@Composable
fun TaskTypeSection(
    selected: String,
    onSelect: (String) -> Unit
) {

    Column {

        Text( text = "2. Choose Task Type",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            Box(Modifier.weight(1f)) {
                TaskTypeCard(
                    title = "Relevant",
                    subtitle = "Real Work",
                    color = Color.Green,
                    selected = selected == "Relevant",
                    onClick = { onSelect("Relevant") }
                )
            }

            Box(Modifier.weight(1f)) {
                TaskTypeCard(
                    title = "Gap",
                    subtitle = "Time Waste",
                    color = Color.Red,
                    selected = selected == "Gap",
                    onClick = { onSelect("Gap") }
                )
            }

            Box(Modifier.weight(1f)) {
                TaskTypeCard(
                    title = "Not Core Task",
                    subtitle = "Important",
                    color = Color(0xFF5F9CFF),
                    selected = selected == "Not Core Task",
                    onClick = { onSelect("Not Core Task") }
                )
            }
        }
    }
}

@Composable
fun TaskTypeCard(
    title: String,
    subtitle: String,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (selected)
                    color.copy(alpha = 0.08f)
                else
                    Color(0xFF0B0F1A)
            )
            .border(
                width = if (selected) 1.dp else 0.5.dp,
                color = if (selected) color else Color(0xFF2A3142),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {

        // Selection Tick
        if (selected) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            // Icon
            Icon(
                imageVector = when (title) {
                    "Relevant" -> Icons.Default.MyLocation
                    "Gap" -> Icons.Default.Close
                    else -> Icons.Default.Star
                },
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(30.dp)
            )

            Text(
                text = title,
                color = color,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "($subtitle)",
                color = color,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = when (title) {
                    "Relevant" ->
                        "Directly moves you closer to your goal."

                    "Gap" ->
                        "Doesn't add value to your mission."

                    else ->
                        "Important but not part of your goal."
                },
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun TaskTypeSectionPreview(){
    TaskTypeSection("aman", onSelect = {})
}