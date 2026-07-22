package com.example.studysiege.ui.Analytics.AnalyticScreenComponents

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun SiegeLevelCard() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF0B0F1A))
            .border(
                1.dp,
                Brush.horizontalGradient(
                    listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                ),
                RoundedCornerShape(20.dp)
            )
            .padding(6.dp)
    ) {

        // 🔥 Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Siege Level", color = Color.White, fontWeight = FontWeight.Bold)
            Icon(Icons.Default.ArrowForward, null, tint = Color.Gray)
        }

        Spacer(Modifier.height(1.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            // 🔥 Level Box
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("12", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.width(3.dp))

            Column {
                Text("Study Warrior", color = Color.White, fontSize = 6.sp)

                Spacer(Modifier.height(1.dp))

                // 🔥 Progress Bar
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.5.dp))
                        .background(Color(0xFF1F2937))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.64f)
                            .height(3.dp)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                                )
                            )
                    )
                }

                Spacer(Modifier.height(.5.dp))

                Text(
                    "3200 / 5000 XP",
                    color = Color.Gray,
                    fontSize = 2.5.sp
                )
            }
        }
    }
}

