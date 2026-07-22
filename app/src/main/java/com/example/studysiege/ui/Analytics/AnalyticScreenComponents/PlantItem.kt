package com.example.studysiege.ui.Analytics.AnalyticScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PlanItem(
    title: String,
    time: String,
    completed: Boolean
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .border(
                        2.dp,
                        if (completed)
                            Color(0xFF00FF88)
                        else
                            Color.Gray,

                        CircleShape
                    )
                    .background(
                        if (completed)
                            Color(0xFF00FF88)
                        else
                            Color.Transparent
                    ),

                contentAlignment = Alignment.Center
            ) {

                if (completed) {

                    Text(
                        text = "✓",
                        fontSize = 7.sp,
                        color = Color.Black
                    )
                }
            }

            Spacer(Modifier.width(8.dp))

            Text(
                text = title,
                color = Color.White,
                fontSize = 11.sp
            )
        }

        Text(
            text = time,
            color = Color.Gray,
            fontSize = 10.sp
        )
    }
}
@Preview
@Composable
fun PlanItemPreview(){
    PlanItem("aman","3",true)
}