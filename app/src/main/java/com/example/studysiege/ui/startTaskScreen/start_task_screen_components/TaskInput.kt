package com.example.studysiege.ui.startTaskScreen.start_task_screen_components


// Core Compose
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle

@Composable
fun TaskNameField(
    taskName: String,
    onValueChange: (String) -> Unit
) {

    Column {

        // 🔥 TITLE
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {



            Text(
                text = "2. What are you working on?",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(8.dp))

        // 🔥 INPUT BOX
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF0B0F1A))
                .border(
                    1.dp,
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFFB14EFF),
                            Color(0xFF5F9CFF)
                        )
                    ),
                    RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Left Icon Box
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFB14EFF),
                                Color(0xFF8A5CFF)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(26.dp)
                    .background(Color.White.copy(alpha = 0.15f))
            )

            Spacer(Modifier.width(12.dp))

            BasicTextField(
                value = taskName,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                ),
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->

                    if (taskName.isEmpty()) {

                        Text(
                            text = "e.g. Physics Chapter 5, Math Practice...",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                    innerTextField()
                }
            )

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Color(0xFFB14EFF),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun showPreview(){
    TaskNameField("ss", onValueChange = {})
}