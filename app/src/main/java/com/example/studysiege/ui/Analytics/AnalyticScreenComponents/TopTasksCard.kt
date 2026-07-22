package com.example.studysiege.ui.Analytics.AnalyticScreenComponents


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysiege.room.entity.TaskEntity

@Composable
fun TopTasksCard(

    tasks: List<TaskEntity>,
    modifier: Modifier=Modifier

) {

    val topTasks =
        tasks
            .filter {

                it.type == "Relevant"

            }
            .groupBy {

                it.title

            }
            .map { (title, taskList) ->

                title to taskList.sumOf {

                    it.elapsedSeconds

                }

            }
            .sortedByDescending {

                it.second

            }
            .take(10)

    val maxTime =
        topTasks
            .maxOfOrNull {

                it.second

            } ?: 1L

    Card(

        modifier = Modifier
            .width(190.dp)
            .height(200.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0B0F1A)
        ),

        shape = RoundedCornerShape(20.dp),

        border = BorderStroke(
            1.dp,
            Brush.horizontalGradient(
                listOf(
                    Color(0xFF8A5CFF),
                    Color(0xFF5F9CFF)
                )
            )
        )

    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)

        ) {

            Text(

                text = "Top Tasks (By Time Spent)",

                color = Color(0xFFB57CFF),

                fontWeight = FontWeight.Bold,

                fontSize = 14.sp

            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(

                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {

                items(topTasks) { task ->

                    val progress =
                        task.second.toFloat() /
                                maxTime.toFloat()

                    TaskProgressItem(

                        title = task.first,

                        time = formatTime(task.second),

                        progress = progress,

                        color = Color(0xFF8A5CFF)

                    )

                }

            }

        }

    }

}