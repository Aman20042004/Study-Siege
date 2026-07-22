package com.example.studysiege.ui.startTaskScreen.start_task_screen_components.WheelPicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDurationBottomSheet(

    onDismiss: () -> Unit,

    onConfirm: (Long) -> Unit

) {
    var showError by remember {
        mutableStateOf(false)
    }

    var hour by remember {
        mutableIntStateOf(0)
    }

    var minute by remember {
        mutableIntStateOf(0)
    }

   var second by remember {
        mutableIntStateOf(0)
    }

    ModalBottomSheet(

        onDismissRequest = onDismiss

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = "Custom Duration",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            Row(

                horizontalArrangement = Arrangement.SpaceEvenly,

                modifier = Modifier.fillMaxWidth()

            ) {

                WheelPicker(

                    value = hour,

                    range = 0..23,

                    onValueChange = {
                        hour = it
                    }
                )

                WheelPicker(

                    value = minute,

                    range = 0..59,

                    onValueChange = {
                        minute = it
                    }
                )

                WheelPicker(

                    value = second,

                    range = 0..59,

                    onValueChange = {
                        second = it
                    }
                )
            }

            Spacer(Modifier.height(20.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.End

            ) {

                TextButton(

                    onClick = onDismiss

                ) {

                    Text("Cancel")
                }

                Spacer(Modifier.width(8.dp))

                if (showError) {

                    Text(

                        text = "Duration must be greater than 0",

                        color = Color.Red,

                        fontSize = 12.sp
                    )

                    Spacer(Modifier.height(8.dp))
                }



                Button(

                    onClick = {

                        val totalSeconds =
                            hour * 3600L +
                                    minute * 60L +
                                    second

                        onConfirm(totalSeconds)
                    }

                ) {

                    Text("OK")
                }
            }

            Spacer(Modifier.height(20.dp))
        }
    }
}