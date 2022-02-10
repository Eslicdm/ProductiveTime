package com.eslirodrigues.productivetime.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.productivetime.core.task.TaskState
import com.eslirodrigues.productivetime.core.task.toTask
import com.eslirodrigues.productivetime.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    val response: MutableState<TaskState> = mutableStateOf(TaskState.Empty)

    init {
        getAllTasks()
    }

    private fun getAllTasks() = viewModelScope.launch {
        taskRepository.getAllTasks()
            .onStart {
                response.value = TaskState.Loading
            }
            .catch {
                response.value = TaskState.Failure(it)
            }.collect { it ->
                val task = it.map { it.toTask()}
                response.value = TaskState.Success(task)
            }
    }

    fun saveTask(id: Long?, task: String, hour: Long, min: Long) = viewModelScope.launch {
        if (task.isNotBlank()) {
            taskRepository.saveTask(id, task, hour, min)
        }
    }

    fun deleteTaskById(id: Long) = viewModelScope.launch {
        taskRepository.deleteTaskById(id)
    }
}