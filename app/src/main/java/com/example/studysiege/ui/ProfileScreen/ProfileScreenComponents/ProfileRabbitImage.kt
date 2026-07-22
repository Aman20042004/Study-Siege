package com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.studysiege.R


@Composable
fun ProfileRabbitImage() {

    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .border(
                2.dp,
                Brush.linearGradient(
                    listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                ),
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_rabbit), // 🔥 image name
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}