package com.example.studysiege.ui.TaskScreen.TaskScreenComponents


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysiege.R


@Composable
fun RunningTaskCard(
    title: String,
    type: String,
    mode: String,
    targetDuration: Long,
    elapsedTime: String,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {

    val accentColor =
        when (type) {
            "Relevant" -> Color(0xFF00FF88)
            "Gap" -> Color(0xFFFF5252)
            else -> Color(0xFF5F9CFF)
        }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0B0F1A)
        ),
        border = BorderStroke(
            1.dp,
            Color(0xFF8A5CFF)
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


            Image(
                painter = painterResource(
                    when (type) {
                        "Gap" -> R.drawable.red_pause_ring
                        "Relevant" -> R.drawable.green_pause_ring
                        else -> R.drawable.blue_pause_ring
                    }
                ),
                contentDescription = null,
                modifier = Modifier.size(110.dp)
            )

            Spacer(Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(90.dp)
                    .background(Color.White.copy(alpha = 0.08f))
            )

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Spacer(Modifier.height(6.dp))

                TypeTag(type)

                Spacer(Modifier.height(4.dp))

                Text(
                    text =
                        if (mode == "Timer")
                            "Target: ${formatDuration(targetDuration)}"
                        else
                            "∞ No Time Limit",
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(6.dp))


                Text(
                    text = formatDuration(elapsedTime.toLong()),
                    color = accentColor,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            }
        }
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = onDelete
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
    }
        }


    }

