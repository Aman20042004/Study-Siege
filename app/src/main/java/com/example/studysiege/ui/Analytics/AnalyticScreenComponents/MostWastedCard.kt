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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysiege.room.entity.TaskEntity

//import com.example.studysiege.database.TaskEntity

@Composable
fun MostWastedCard(

    tasks: List<TaskEntity>,
    modifier: Modifier = Modifier

) {

    val wastedTasks =
        tasks
            .filter {

                it.type == "Gap"

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
        wastedTasks
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
                    Color(0xFFFF5252),
                    Color(0xFFFF8A80)
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

                text = "Most Wasted On",

                color = Color.Red,

                fontWeight = FontWeight.Bold,

                fontSize = 14.sp

            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(

                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {

                items(wastedTasks) { task ->

                    val progress =
                        task.second.toFloat() /
                                maxTime.toFloat()

                    TaskProgressItem(

                        title = task.first,

                        time = formatTime(task.second),

                        progress = progress,

                        color = Color(0xFFFF5252)

                    )

                }

            }

        }

    }

}