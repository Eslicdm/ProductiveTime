package com.eslirodrigues.productivetime.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eslirodrigues.productivetime.R
import com.eslirodrigues.productivetime.core.ScreenNav
import com.eslirodrigues.productivetime.ui.theme.DarkOrange
import com.eslirodrigues.productivetime.ui.theme.LightOrange
import com.eslirodrigues.productivetime.ui.theme.PrimaryOrange
import com.eslirodrigues.productivetime.ui.viewmodel.TaskViewModel

@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    var inputTask by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .background(LightOrange)
        .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .background(PrimaryOrange)
                .border(1.dp, DarkOrange)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    navController.navigate(ScreenNav.Task.route)
                },
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .width(80.dp)
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        tint = Color.Red,
                        contentDescription = stringResource(id = R.string.cancel)
                    )
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            IconButton(onClick = {
                viewModel.saveTask(id = null, inputTask)
                navController.navigate(ScreenNav.Task.route)
            },
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .width(60.dp)
            ) {
                Row {
                    Text(
                        text = stringResource(id = R.string.save),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.Check,
                        tint = Color.Green,
                        contentDescription = stringResource(id = R.string.save)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .border(1.dp, PrimaryOrange),
                value = inputTask,
                onValueChange = {
                    inputTask = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.task),
                        modifier = Modifier
                            .width(250.dp),
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    placeholderColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                )
            )
        }
    }
}