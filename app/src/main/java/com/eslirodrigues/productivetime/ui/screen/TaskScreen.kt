package com.eslirodrigues.productivetime.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.productivetime.R
import com.eslirodrigues.productivetime.core.task.TaskState
import com.eslirodrigues.productivetime.ui.viewmodel.TaskViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Destination(start = true)
@Composable
fun TaskScreen(
    navigator: DestinationsNavigator,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val showAddTaskAlertDialog = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showAddTaskAlertDialog.value = !showAddTaskAlertDialog.value
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        }
    ) {
        if(showAddTaskAlertDialog.value) AddTaskAlertDialog(showAddTaskAlertDialog)
        when(val result = viewModel.response.value) {
            is TaskState.Success -> {
                LazyColumn {
                    items(result.data) { task ->
                        val state = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    viewModel.deleteTaskById(task.id)
                                    scope.launch {
                                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Delete task was successful",
                                            actionLabel = "Undo"
                                        )
                                        when(snackbarResult) {
                                            SnackbarResult.Dismissed -> Log.d("Snackbar", "Dismissed")
                                            SnackbarResult.ActionPerformed -> viewModel.saveTask(task.id, task.task, task.hour, task.min)
                                        }
                                    }
                                }
                                true
                            }
                        )
                        SwipeToDismiss(
                            state = state,
                            background = {
                                val color = when (state.dismissDirection) {
                                    DismissDirection.StartToEnd -> Color.Transparent
                                    DismissDirection.EndToStart -> Color.Red
                                    null -> Color.Transparent
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = color)
                                        .padding(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.White,
                                        modifier = Modifier.align(Alignment.CenterEnd)
                                    )
                                }
                            },
                            dismissContent = {
                                TaskItemScreen(task = task, navigator)
                            },
                            directions = setOf(DismissDirection.EndToStart)
                        )
                    }
                }
            }
            is TaskState.Failure -> {
                Text(text = "${result.msg}")
            }
            is TaskState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is TaskState.Empty -> {
                Text(text = "Empty")
            }
        }
    }
}