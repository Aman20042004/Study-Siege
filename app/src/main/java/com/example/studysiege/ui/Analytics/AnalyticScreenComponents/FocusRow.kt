package com.example.studysiege.ui.Analytics.AnalyticScreenComponents


import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FocusRow(

    color: Color,

    title: String,

    value: String

) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically

    ) {

        Box(

            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)

        )

        Spacer(Modifier.width(10.dp))

       Text(

            text = title,

            color = Color.White,

            fontSize = 12.sp

        )

        Spacer(Modifier.width(40.dp))

        Text(

            text = value,

            color = color,

            fontWeight = FontWeight.Bold,

            fontSize = 12.sp

        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF05070D
)
@Composable
fun FocusRowPreview() {

    FocusRow(
        color = Color(0xFF00FF88),
        title = "Excellent Focus",
        value = "18 Days"
    )
}