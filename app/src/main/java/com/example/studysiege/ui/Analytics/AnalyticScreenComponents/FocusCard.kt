package com.example.studysiege.ui.Analytics.AnalyticScreenComponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysiege.R


@Composable
fun FocusCard(
    relevantSeconds: Long,
    dailyGoalHours: Int
) {

    val goalSeconds = dailyGoalHours * 3600L

    val focusScore =
        if (goalSeconds == 0L) {
            0
        } else {
            ((relevantSeconds * 100) / goalSeconds)
                .coerceAtMost(100)
                .toInt()
        }

    val progress = focusScore / 100f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {

        // 🔥 LEFT CARD
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF0B0F1A))
                .border(
                    1.dp,
                    Brush.linearGradient(
                        listOf(Color(0xFF8A5CFF), Color(0xFF00D4FF))
                    ),
                    RoundedCornerShape(20.dp)
                )
                .padding(16.dp)
        ) {

            Column {

                // 🔥 TITLE + ARROW
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text("Today’s Focus", color = Color.Gray, fontSize = 12.sp)

                    Icon(
                        Icons.Default.ArrowForward,
                        null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Spacer(Modifier.height(12.dp))

                // 🔥 PROGRESS
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    Canvas(modifier = Modifier.size(150.dp)) {

                        val stroke = 14.dp.toPx()
                        val sweep = progress * 360f

                        // background circle
                        drawArc(
                            color = Color(0xFF1F2937),
                            startAngle = 0f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(stroke, cap = StrokeCap.Round)
                        )

                        // progress arc
                        drawArc(
                            brush = Brush.sweepGradient(
                                listOf(
                                    Color(0xFF00FF88),
                                    Color(0xFF8A5CFF),
                                    Color(0xFF00D4FF)
                                )
                            ),
                            startAngle = -90f,
                            sweepAngle = sweep,
                            useCenter = false,
                            style = Stroke(stroke, cap = StrokeCap.Round)
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "$focusScore",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text("Focus Score", color = Color.Gray, fontSize = 11.sp)
                    }
                }

                Spacer(Modifier.height(13.dp))

            }



        }

        Spacer(Modifier.width(12.dp))

        // 🔥 RIGHT SIDE (Rabbit + Bubble)
        Column {

            Image(
                painter = painterResource(R.drawable.analytics_rabbit),
                contentDescription = null,
                modifier = Modifier.size(157.dp)
            )

            Spacer(Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF0B0F1A))
                    .border(
                        1.dp,
                        Brush.horizontalGradient(
                            listOf(Color(0xFF00FF88), Color(0xFF8A5CFF))
                        ),
                        RoundedCornerShape(14.dp)
                    )
                    .padding(10.dp)
            ) {

                Column {
                    Text(
                        "Rabbit says",
                        color = Color(0xFF00FF88),
                        fontSize = 11.sp
                    )

                    Text(
                        "Distractions don’t build dreams.\nDiscipline does!",
                        color = Color.White,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
