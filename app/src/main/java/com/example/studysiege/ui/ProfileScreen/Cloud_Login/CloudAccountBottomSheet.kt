package com.example.studysiege.ui.ProfileScreen.Cloud_Login






import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloudAccountBottomSheet(

    sheetState: SheetState = rememberModalBottomSheetState(),

    isLoggedIn: Boolean,

    studyId: String,

    loading: Boolean,

    message: String,

    onDismiss: () -> Unit,

    onLogin: (String, String) -> Unit,

    onRegister: (String, String) -> Unit,

    onLogout: () -> Unit,

    onSyncNow: () -> Unit

) {

    var id by remember {

        mutableStateOf(studyId)

    }

    var password by remember {

        mutableStateOf("")

    }

    ModalBottomSheet(

        onDismissRequest = onDismiss,

        sheetState = sheetState,

        containerColor = Color(0xFF0B0D18),

        dragHandle = null

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            Text(

                text = "Cloud Account",

                style = MaterialTheme.typography.headlineSmall,

                color = Color.White

            )

            HorizontalDivider(
                color = Color(0xFF4B3DD1)
            )
            if (!isLoggedIn) {

                Icon(
                    imageVector = Icons.Default.CloudOff,
                    contentDescription = null,
                    tint = Color(0xFF8B5CF6),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(

                    value = id,

                    onValueChange = {
                        id = it
                    },

                    label = {
                        Text("Study ID")
                    },

                    singleLine = true,

                    modifier = Modifier.fillMaxWidth(),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color(0xFF8B5CF6),
                        unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = Color(0xFF8B5CF6),
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color(0xFF8B5CF6)
                    )

                )

                OutlinedTextField(

                    value = password,

                    onValueChange = {
                        password = it
                    },

                    label = {
                        Text("Password")
                    },

                    visualTransformation = PasswordVisualTransformation(),

                    singleLine = true,

                    modifier = Modifier.fillMaxWidth(),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color(0xFF8B5CF6),
                        unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = Color(0xFF8B5CF6),
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color(0xFF8B5CF6)
                    )

                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = {

                        onLogin(id, password)

                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(16.dp),

                    colors = ButtonDefaults.buttonColors(

                        containerColor = Color(0xFF7C4DFF)

                    )

                ) {

                    if (loading) {

                        CircularProgressIndicator(

                            color = Color.White,

                            strokeWidth = 2.dp

                        )

                    } else {

                        Text("Login")

                    }

                }

                Button(

                    onClick = {
                        onRegister(id, password)
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(16.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00C853)
                    )

                ) {

                    Text("Create New Cloud Account")

                }

            }

            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = Color.Red
                )
            }
            else {

                Icon(
                    imageVector = Icons.Default.CloudDone,
                    contentDescription = null,
                    tint = Color(0xFF00E676),
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Connected",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                Text(
                    text = studyId,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF8B5CF6)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onSyncNow,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7C4DFF)
                    )
                ) {
                    Text("Upload")
                }

                Button(
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB00020)
                    )
                ) {
                    Text("Logout")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}