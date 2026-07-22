package com.example.studysiege.ui.TaskScreen.TaskScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskTabs(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF111827))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        listOf("All", "Running", "Paused", "Completed").forEach { tab ->

            val isSelected = tab == selectedTab

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (isSelected)
                            Color(0xFF8A5CFF)
                        else
                            Color.Transparent
                    )
                    .clickable {
                        onTabSelected(tab)
                    }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = tab,
                    color = if (isSelected)
                        Color.White
                    else
                        Color.Gray
                )
            }
        }
    }
}