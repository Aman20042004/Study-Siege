package com.example.studysiege.ui.Calendar.CalendarComponents


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MonthlyOverviewSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(Color(0xFF0B0F1A))
            .border(
                1.dp,
                Brush.verticalGradient(
                    listOf(Color(0xFF8A5CFF), Color.Transparent)
                ),
                RoundedCornerShape(22.dp)
            )
            .padding(16.dp)
    ) {

        // 🔥 HEADER (FIXED ALIGNMENT)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            OverviewCard(
                "Total Days",
                "31",
                "Days",
                Color(0xFF00D4FF),
                Icons.Default.RadioButtonChecked,
                modifier = Modifier.weight(1f)
            )

            OverviewCard(
                "High Focus",
                "12",
                "Days (38%)",
                Color(0xFF00FF88),
                Icons.Default.CheckCircle,
                modifier = Modifier.weight(1f)
            )

            OverviewCard(
                "Average",
                "9",
                "Days (29%)",
                Color(0xFFFFC107),
                Icons.Default.Timeline,
                modifier = Modifier.weight(1f)
            )

            OverviewCard(
                "Low Focus",
                "6",
                "Days (19%)",
                Color(0xFFFF5252),
                Icons.Default.SentimentDissatisfied,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(12.dp))

        // 🔥 SINGLE SMOOTH PROGRESS BAR (FIXED)
        SmoothProgressBar()

        Spacer(Modifier.height(8.dp))

        // 🔥 TROPHY MESSAGE (FIXED CLEAN)
        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(Icons.Default.EmojiEvents, null, tint = Color(0xFF8A5CFF))

            Spacer(Modifier.width(5.dp))

            Text("You're in the top ", color = Color.Gray, fontSize = 12.sp)

            Text(
                "24%",
                color = Color(0xFF8A5CFF),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            Text(
                " of focused warriors this month!",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun OverviewCard(
    title: String,
    value: String,
    subtitle: String,
    color: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF0B0F1A), Color(0xFF111827))
                )
            )
            .border(
                1.dp,
                Brush.linearGradient(
                    listOf(color.copy(0.6f), Color.Transparent)
                ),
                RoundedCornerShape(16.dp)
            )
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔥 ICON CIRCLE
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = color)
        }

        Spacer(Modifier.height(8.dp))

        // 🔥 TITLE (NOW COLORED — IMPORTANT FIX)
        Text(
            text = title,
            color = color, //
            fontSize = 8.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(4.dp))

        // 🔥 VALUE (BIG NUMBER)
        Text(
            text = value,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        // 🔥 SUBTEXT
        Text(
            text = subtitle,
            color = Color.Gray,
            fontSize = 6.sp
        )
    }
}

@Composable
fun SmoothProgressBar() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(7.dp)
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF1F2937))
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.38f)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF00FF88), Color(0xFFFFC107), Color(0xFFFF5252))
                    )
                )
        )
    }
}
