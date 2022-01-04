package com.eslirodrigues.productivetime.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.productivetime.R
import com.eslirodrigues.productivetime.ui.theme.LightOrange
import com.eslirodrigues.productivetime.ui.viewmodel.TaskViewModel

@Composable
fun AddTaskAlertDialog(
    showAddTaskAlertDialog: MutableState<Boolean>,
    viewModel: TaskViewModel = hiltViewModel()
)  {
    var inputTask by remember { mutableStateOf("") }
    var hour by remember { mutableStateOf("") }
    var min by remember { mutableStateOf("") }

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        onDismissRequest = { showAddTaskAlertDialog.value = false },
        title = null,
        text = {
            Column {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(
                        Icons.Default.AddAlarm,
                        tint = Color.White,
                        contentDescription = stringResource(id = R.string.add_task)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = R.string.add_task),
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    value = inputTask,
                    onValueChange = {
                        if (it.length <= 24) inputTask = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.task),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    },
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 60.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.width(50.dp),
                        value = hour,
                        onValueChange = {
                            if (it.length <= 2) hour = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.hr),
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier.padding(top = 19.dp, start = 5.dp, end = 5.dp),
                        text = ":",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        modifier = Modifier.width(50.dp),
                        value = min,
                        onValueChange = {
                            if (it.length <= 2) min = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.min),
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        maxLines = 1
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        end = 15.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                onClick = {
                    viewModel.saveTask(id = null, inputTask, hour.toLong(), min.toLong())
                    showAddTaskAlertDialog.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.confirm).uppercase(),
                    color = Color.White,
                )
            }
        },
        dismissButton = {
            TextButton(
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                onClick = {
                    showAddTaskAlertDialog.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.cancel).uppercase(),
                    color = Color.White,
                )

            }
        },
        backgroundColor = LightOrange,
        contentColor = Color.White,
        shape = RoundedCornerShape(25.dp)
    )
}