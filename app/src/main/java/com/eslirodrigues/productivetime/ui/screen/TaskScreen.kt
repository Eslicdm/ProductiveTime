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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eslirodrigues.productivetime.R
import com.eslirodrigues.productivetime.core.ScreenNav
import com.eslirodrigues.productivetime.core.TaskState
import com.eslirodrigues.productivetime.ui.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(ScreenNav.AddTask.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        }
    ) {
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
                                            SnackbarResult.ActionPerformed -> viewModel.saveTask(task.id, task.task)
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
                                TaskItemScreen(task = task)
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