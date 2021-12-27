package com.eslirodrigues.productivetime.core

import productivetime.taskdb.TaskEntity

sealed class TaskState {
    data class Success(val data: List<TaskEntity>) : TaskState()
    data class Failure(val msg: Throwable) : TaskState()
    object Loading : TaskState()
    object Empty : TaskState()
}