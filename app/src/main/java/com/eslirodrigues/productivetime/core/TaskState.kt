package com.eslirodrigues.productivetime.core

import com.eslirodrigues.productivetime.data.datasource.Task

sealed class TaskState {
    data class Success(val data: List<Task>) : TaskState()
    data class Failure(val msg: Throwable) : TaskState()
    object Loading : TaskState()
    object Empty : TaskState()
}