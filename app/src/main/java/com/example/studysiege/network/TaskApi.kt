package com.example.studysiege.network



import com.example.studysiege.network.model.Task
import com.example.studysiege.network.model.TaskUploadRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskApi {

    @POST("api/tasks/upload")
    suspend fun uploadTasks(
        @Body request: TaskUploadRequest
    ): Response<String>

    @GET("api/tasks/download/{studyId}")
    suspend fun downloadTasks(
        @Path("studyId") studyId: String
    ): Response<List<Task>>
}