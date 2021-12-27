package com.eslirodrigues.productivetime.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eslirodrigues.productivetime.R
import com.eslirodrigues.productivetime.core.ScreenNav
import com.eslirodrigues.productivetime.core.TaskState
import com.eslirodrigues.productivetime.ui.viewmodel.TaskViewModel

@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {

    Scaffold(
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
                        TaskItemScreen(task = task)
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