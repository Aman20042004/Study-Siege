package com.example.studysiege.network.mapper




import com.example.studysiege.network.ApiClient
import com.example.studysiege.network.model.TaskUploadRequest
import com.example.studysiege.room.dao.TaskDao

class CloudRepository(

    private val taskDao: TaskDao

) {

    suspend fun uploadToCloud(studyId: String) {

        val unsyncedTasks = taskDao.getUnsyncedTasks()

        if (unsyncedTasks.isEmpty()) return

        val request = TaskUploadRequest(
            studyId = studyId,
            tasks = unsyncedTasks.map { it.toNetworkTask() }
        )

        val response = ApiClient.taskApi.uploadTasks(request)

        if (response.isSuccessful) {
            taskDao.markTasksAsSynced(
                unsyncedTasks.map { it.id }
            )
        }
    }

    suspend fun downloadFromCloud(studyId: String) {

        val response = ApiClient.taskApi.downloadTasks(studyId)

        if (response.isSuccessful) {

            val tasks = response.body()?.map {
                it.toEntity()
            } ?: emptyList()

            taskDao.insertAll(tasks)
        }
    }
}