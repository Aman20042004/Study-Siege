package com.example.studysiege.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.studysiege.R
import com.example.studysiegeui.ui.model.Screen


@Composable
fun WelcomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF05070D),
                        Color(0xFF0B0F1A),
                        Color(0xFF120F2A)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            // TITLE
            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)
                    ) { append("Study") }

                    withStyle(
                        SpanStyle(
                            brush = Brush.horizontalGradient(
                                listOf(Color(0xFF8A5CFF), Color(0xFFB77CFF))
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    ) { append("Rabbit") }
                },
                fontSize = 36.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                "Stay focused. Win your day.",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // RABBIT IMAGE
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rabbit),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = 25.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // FEATURE CARD
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.White.copy(alpha = 0.06f))
                    .padding(vertical = 22.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FeatureItem("🎯", "Focus Deeply", "One task at a time")
                DividerLine()
                FeatureItem("🛡️", "Stay Honest", "Track your truth")
                DividerLine()
                FeatureItem("🏆", "Win Daily", "Small wins matter")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔥 BUTTON (FIXED)
            ElevatedButton(
                onClick = {navController.navigate(Screen.StartTask.route)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Transparent
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
                contentPadding = PaddingValues(0.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF7B4DFF),
                                    Color(0xFF5F9CFF)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Text(
                                "Begin Your Journey",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                "Start your next attack",
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 12.sp
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Text("➜", fontSize = 18.sp, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // FOOTER
            Text(
                "Distractions are the enemy.\nDiscipline is your weapon.",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun FeatureItem(icon: String, title: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(icon, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            title,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            subtitle,
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 11.sp
        )
    }
}

@Composable
fun DividerLine() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(40.dp)
            .background(Color.White.copy(alpha = 0.1f))
    )
}

