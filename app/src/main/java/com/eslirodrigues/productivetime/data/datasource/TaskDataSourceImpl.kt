package com.eslirodrigues.productivetime.data.datasource

import com.eslirodrigues.productivetime.TaskDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import productivetime.taskdb.TaskEntity
import javax.inject.Inject

class TaskDataSourceImpl @Inject constructor(
    db: TaskDatabase
) : TaskDataSource {

    private val queries = db.taskEntityQueries

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return queries.getAllTasks().asFlow().mapToList()
    }

    override suspend fun saveTask(id: Long?, task: String) {
        queries.saveTask(id = null, task)
    }

    override suspend fun deleteTaskById(id: Long) {
        queries.deleteTaskById(id)
    }
}