package com.example.studysiege.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.studysiegeui.ui.model.Screen

//@Preview(showSystemUi =true)
@Composable
fun BottomNavBar(navController: NavController){
//fun BottomNavBar(){
    val currentRoute =
        navController.currentBackStackEntryAsState().value
            ?.destination?.route
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .navigationBarsPadding()
            .padding(bottom=0.dp)
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF0B0F1A)).border(
                width = 0.5.dp,
                color = Color.White.copy(alpha = 0.08f),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 🔥 HOME
            NavItem(
                icon = Icons.Default.Home,
                label = "Home",
                selected = currentRoute == Screen.Home.route,
                onClick = {
                    navController.navigate(Screen.Home.route)
                }
            )

            // 🔥 ANALYTICS
            NavItem(
                icon = Icons.Default.BarChart,
                label = "Analytics",
                selected = currentRoute == Screen.Analytics.route,
                onClick = {
                    navController.navigate(Screen.Analytics.route)
                }
            )

            // 🔥 CENTER BUTTON
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            listOf(
                                Color(0xFF8A5CFF),
                                Color(0xFF5F9CFF)
                            )
                        )
                    )
                    .clickable {
                        navController.navigate(Screen.StartTask.route)
                    },

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            // 🔥 CALENDAR
            NavItem(
                icon = Icons.Default.CalendarMonth,
                label = "Calendar",
                selected = currentRoute == Screen.Calendar.route,
                onClick = {
                    navController.navigate(Screen.Calendar.route)
                }
            )

            // 🔥 PROFILE
            NavItem(
                icon = Icons.Default.Person,
                label = "Profile",
                selected = currentRoute == Screen.Profile.route,
                onClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
    }
}


@Composable
fun NavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier.clickable {
            onClick()
        },

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected)
                Color(0xFF8A5CFF)
            else
                Color.Gray
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            fontSize = 10.sp,
            color = if (selected)
                Color(0xFF8A5CFF)
            else
                Color.Gray
        )
    }
}