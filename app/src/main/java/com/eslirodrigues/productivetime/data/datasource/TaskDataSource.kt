package com.eslirodrigues.productivetime.data.datasource

import kotlinx.coroutines.flow.Flow
import productivetime.taskdb.TaskEntity

interface TaskDataSource {

    fun getAllTasks(): Flow<List<TaskEntity>>

    suspend fun saveTask(id: Long?, task: String)

    suspend fun deleteTaskById(id: Long)
}