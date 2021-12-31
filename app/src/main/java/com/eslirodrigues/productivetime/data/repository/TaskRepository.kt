package com.eslirodrigues.productivetime.data.repository

import com.eslirodrigues.productivetime.data.datasource.TaskDataSourceImpl
import kotlinx.coroutines.flow.Flow
import productivetime.taskdb.TaskEntity
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDataSourceImpl: TaskDataSourceImpl
) {
    fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDataSourceImpl.getAllTasks()
    }

    suspend fun saveTask(id: Long?, task: String, hour: Long, min: Long) {
        taskDataSourceImpl.saveTask(id = null, task, hour, min)
    }

    suspend fun deleteTaskById(id: Long) {
        taskDataSourceImpl.deleteTaskById(id)
    }
}