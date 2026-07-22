package com.example.studysiege.ui.startTaskScreen.start_task_screen_components


// Core Compose
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Layout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

// UI
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Graphics
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush

// Shapes
import androidx.compose.foundation.shape.RoundedCornerShape

// Background, Border, Clip
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.draw.clip

// Text & Material
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

// Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle

// Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource

// Font
import androidx.compose.ui.text.font.FontWeight

// Scroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.ModeSection

@Composable
fun ModeSection(
    selectedMode: String,
    onModeChange: (String) -> Unit,
    selectedDuration: String,
    customDurationText: String,
    onDurationChange: (String) -> Unit
) {

    Column {

        // 🔥 TITLE
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                "3. Choose Mode",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.width(6.dp))

            Icon(
                Icons.Default.HelpOutline,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(14.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        // 🔥 MODE CARDS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            // 🔥 STOPWATCH
            ModeCard(
                modifier = Modifier.weight(1f),
                selected = selectedMode == "Stopwatch",
                icon = Icons.Default.Timer,
                title = "Stopwatch",
                subtitle = "Track time without\n a target.",
                iconColor = Color(0xFFB14EFF),
                onClick = {
                    onModeChange("Stopwatch")
                }
            )

            // 🔥 TIMER
            ModeCard(
                modifier = Modifier.weight(1f),
                selected = selectedMode == "Timer",
                icon = Icons.Default.TrackChanges,
                title = "Timer",
                subtitle = "Set a goal time and\nstay on track.",
                iconColor = Color(0xFF2F80FF),
                onClick = {
                    onModeChange("Timer")
                }
            )
        }

        Spacer(Modifier.height(5.dp))

        // 🔥 DURATION SECTION


        if (selectedMode == "Timer") {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0xFF0B0F1A))
                    .border(
                        1.dp,
                        Color.White.copy(alpha = 0.08f),
                        RoundedCornerShape(2.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0xFF0B0F1A))
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.08f),
                            RoundedCornerShape(2.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {



                }
        }





            Column {

                Text(
                    "Select Duration",
                    color = Color.Gray,
                    fontSize = 10.sp
                )

                Spacer(Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    DurationChip(
                        modifier = Modifier.weight(1f),
                        text = "30 min",
                        selected = selectedDuration == "30m",
                        onClick = {
                            onDurationChange("30m")
                        }
                    )

                    DurationChip(
                        modifier = Modifier.weight(1f),
                        text = "1 hour",
                        selected = selectedDuration == "1h",
                        onClick = {
                            onDurationChange("1h")
                        }
                    )

                    DurationChip(
                        modifier = Modifier.weight(1f),
                        text = "2 hours",
                        selected = selectedDuration == "2h",
                        onClick = {
                            onDurationChange("2h")
                        }
                    )

                    DurationChip(
                        modifier = Modifier.weight(1f),
                        text = "3 hours",
                        selected = selectedDuration == "3h",
                        onClick = {
                            onDurationChange("3h")
                        }
                    )

                    DurationChip(
                        modifier = Modifier.weight(1f),

                        text =
                            if (selectedDuration == "custom")
                                customDurationText
                            else
                                "Custom",

                        selected = selectedDuration == "custom",

                        onClick = {

                            onDurationChange("custom")
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun ModeCard(
    modifier: Modifier = Modifier,
    selected: Boolean,
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconColor: Color,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .height(55.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (selected)
                    Brush.radialGradient(
                        listOf(
                            Color(0xFFB14EFF).copy(alpha = 0.18f),
                            Color(0xFF0B0F1A)
                        )
                    )
                else
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF0B0F1A),
                            Color(0xFF0B0F1A)
                        )
                    )
            )
            .border(
                1.dp,
                if (selected)
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFFB14EFF),
                            Color(0xFF5F9CFF)
                        )
                    )
                else
                    Brush.horizontalGradient(
                        listOf(
                            Color.White.copy(alpha = 0.08f),
                            Color.White.copy(alpha = 0.08f)
                        )
                    ),
                RoundedCornerShape(6.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )

            Spacer(Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = subtitle,
                    color = Color.Gray,
                    fontSize = 9.sp,
                    lineHeight = 10.sp
                )
            }

            if (selected) {

                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFB14EFF)),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(11.dp)
                    )
                }
            } else {

                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .border(
                            1.dp,
                            Color.Gray,
                            CircleShape
                        )
                )
            }
        }
    }
}

@Composable
fun DurationChip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .height(20.dp)
           .clip(RoundedCornerShape(2.dp))
            .background(
                if (selected)
                    Color(0xFF7B4DFF).copy(alpha = 0.22f)
                else
                    Color.Transparent
            )
            .border(
                1.dp,
                if (selected)
                    Color(0xFF8A5CFF)
                else
                    Color.White.copy(alpha = 0.08f),
                RoundedCornerShape(2.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = if (selected) Color.White else Color.Gray,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
