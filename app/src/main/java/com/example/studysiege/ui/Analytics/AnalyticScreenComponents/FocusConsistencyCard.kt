package com.example.studysiege.ui.Analytics.AnalyticScreenComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysiege.navigation.model.FocusConsistencyData

@Composable
fun FocusConsistencyCard(
modifier: Modifier = Modifier,
data: FocusConsistencyData

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(178.dp),

        shape = RoundedCornerShape(12.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0B0F1A)
        ),

        border = BorderStroke(
            1.dp,
            Color(0xFF8A5CFF)
        )

    ) {
        Row(modifier=Modifier.fillMaxSize()){

            Column(modifier=Modifier.weight(.8f)){

               Spacer(Modifier.height(20.dp))

                Text(

                    text = " Focus Consistency",

                    color = Color(0xFFB57CFF),

                    fontSize = 14.sp,

                    fontWeight = FontWeight.Bold,

                    modifier= Modifier.fillMaxWidth(),

                    textAlign= TextAlign.Center

                )

                Spacer(Modifier.height(18.dp))

                Text(

                    text = data.currentStreak.toString(),

                    color = Color(0xFFB57CFF),

                    fontSize = 72.sp,

                    fontWeight = FontWeight.ExtraBold,

                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )

                Text(

                    text = "Current Streak",

                    color = Color.White,

                    fontSize = 13.sp,

                    fontWeight = FontWeight.Bold,

                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )

                Spacer(Modifier.height(4.dp))

                Text(

                    text = "\"${data.currentStreak} Green Days\"",

                    color = Color(0xFF00FF88),

                    fontSize = 10.sp,

                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )

            }
                 Spacer(modifier=Modifier.width(12.dp))
            Column(modifier= Modifier.weight(1.2f)){

                Spacer(modifier=modifier.height(5.dp))

                Row(modifier=Modifier.fillMaxWidth()){

                        SmallStatChip(
                            modifier = Modifier.weight(1f).height(47.dp),
                            emoji = "🕒",
                            title = "History",
                            value = "Coming.."
                        )
                      Spacer(modifier=Modifier.width(5.dp))

                        SmallStatChip(
                            modifier = Modifier.weight(1f).height(47.dp),
                            emoji = "👑",
                            title = "Best Streak",
                            value = "${data.bestStreak} Days"
                        )
                    Spacer(Modifier.width(5.dp))
                    }
                 Spacer(modifier=Modifier.height(10.dp))
                Row(modifier=Modifier.fillMaxWidth()){

                    Column(modifier=Modifier.fillMaxWidth()) {
                        FocusRow(
                            color = Color(0xFF00FF88),
                            title = "Excellent Focus",
                            value = "${data.greenDays} Days"
                        )

                        Spacer(Modifier.height(10.dp))

                        FocusRow(
                            color = Color(0xFFFFC107),
                            title = "Average Focus",
                            value = "${data.yellowDays} Days"
                        )

                        Spacer(Modifier.height(10.dp))

                        FocusRow(
                            color = Color(0xFFFF5252),
                            title = "Wasted Days",
                            value = "${data.redDays} Days"
                        )

                        Spacer(Modifier.height(15.dp))

                        Text(

                            text = "\"Consistency defeats intensity.\"",

                            color = Color(0xFFB57CFF),

                            fontSize = 12.sp,

                            modifier = Modifier.align(Alignment.CenterHorizontally)

                        )
                    }
                }

            }

        }
    }
}


