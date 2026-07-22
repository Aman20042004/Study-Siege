package com.example.studysiege.repository




import com.example.studysiege.network.mapper.CloudRepository
import com.example.studysiege.room.dao.TaskDao

class TaskRepository(

    private val taskDao: TaskDao,
    private val cloudRepository: CloudRepository

) {


    suspend fun uploadToCloud(
        studyId: String
    ) {
        cloudRepository.uploadToCloud(studyId)
    }

    suspend fun downloadFromCloud(
        studyId: String
    ) {
        cloudRepository.downloadFromCloud(studyId)
    }
}