package com.example.studysiege.viewModel




import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studysiege.data.datastore.CloudPreferences

import com.example.studysiege.network.ApiClient
import com.example.studysiege.network.model.LoginRequest
import com.example.studysiege.network.model.RegisterRequest
import com.example.studysiege.repository.TaskRepository
import kotlinx.coroutines.launch

class CloudViewModel(
    private val preferences: CloudPreferences,
    private val repository: TaskRepository

) : ViewModel() {

    var isLoggedIn by mutableStateOf(false)
        private set

    var studyId by mutableStateOf("")
        private set

    var loading by mutableStateOf(false)
        private set

    var message by mutableStateOf("")
        private set

    init {

        viewModelScope.launch {

            preferences.isLoggedIn.collect {

                isLoggedIn = it

            }

        }

        viewModelScope.launch {

            preferences.studyId.collect {

                studyId = it

            }

        }

    }

    fun login(

        id: String,

        password: String

    ) {

        viewModelScope.launch {

            loading = true

            try {

                val response =

                    ApiClient.authApi.login(

                        LoginRequest(id, password)

                    )

                if (response.isSuccessful &&
                    response.body()?.success == true
                ) {

                    isLoggedIn = true

                    studyId = id

                    preferences.saveLogin(id)

                    repository.downloadFromCloud(id)

                    repository.uploadToCloud(id)

                    message = "Cloud Sync Completed"

                } else {

                    message =
                        response.body()?.message
                            ?: "Login Failed"

                }

            } catch (e: Exception) {

                message = e.message ?: "Unknown Error"

            }

            loading = false

        }
    }

    fun register(

        id: String,

        password: String

    ) {

        viewModelScope.launch {

            loading = true

            try {

                val response =

                    ApiClient.authApi.register(

                        RegisterRequest(id, password)

                    )

                if (response.isSuccessful &&
                    response.body()?.success == true
                ) {

                    login(id, password)

                } else {

                    message =
                        response.body()?.message
                            ?: "Registration Failed"

                }

            } catch (e: Exception) {

                message = e.message ?: "Unknown Error"

            }

            loading = false

        }
    }

    fun syncNow() {

        if (!isLoggedIn) return

        viewModelScope.launch {

            repository.downloadFromCloud(studyId)

            repository.uploadToCloud(studyId)

            message = "Sync Complete"

        }
    }

    fun logout() {

        viewModelScope.launch {

            preferences.logout()

            isLoggedIn = false

            studyId = ""

            message = "Logged Out"

        }

    }
}