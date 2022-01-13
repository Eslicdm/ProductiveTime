package com.eslirodrigues.productivetime.core

import com.eslirodrigues.productivetime.data.datasource.Task
import productivetime.taskdb.TaskEntity

fun TaskEntity.toTask() : Task  {
    return Task(
        id = id,
        task = task,
        hour = hour,
        min = min
    )
}