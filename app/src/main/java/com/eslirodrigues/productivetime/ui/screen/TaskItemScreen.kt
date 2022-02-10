package com.eslirodrigues.productivetime.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.productivetime.R
import com.eslirodrigues.productivetime.core.timer.TimeFormatExt.timeFormat
import com.eslirodrigues.productivetime.data.datasource.Task
import com.eslirodrigues.productivetime.ui.screen.destinations.TaskTimerScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.concurrent.TimeUnit


@ExperimentalMaterialApi
@Composable
fun TaskItemScreen(
    task: Task,
    navigator: DestinationsNavigator
) {

    val hourToMillis = TimeUnit.HOURS.toMillis(task.hour)
    val minToMillis = TimeUnit.MINUTES.toMillis(task.min)

    val initialTotalTimeInMillis = hourToMillis + minToMillis

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(150.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(4.dp))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .width(270.dp)
                        .height(200.dp)
                        .padding(10.dp),
                    text = task.task,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    maxLines = 4,
                )
            }
        }
        Box(
            modifier = Modifier.padding(10.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column {
                IconButton(
                    onClick = {
                        navigator.navigate(TaskTimerScreenDestination(task))
                    }
                ) {
                    Column {
                        Text(
                            modifier = Modifier.padding(start = 5.dp, bottom = 10.dp),
                            text = initialTotalTimeInMillis.timeFormat(),
                        )
                        Icon(
                            modifier = Modifier
                                .width(80.dp)
                                .padding(bottom = 5.dp)
                                .size(50.dp),
                            imageVector = Icons.Default.PlayCircleOutline,
                            contentDescription = stringResource(id = R.string.start_task),
                        )
                    }
                }
            }
        }
    }
}