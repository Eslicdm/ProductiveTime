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
import androidx.compose.ui.unit.dp
import com.eslirodrigues.productivetime.R
import com.eslirodrigues.productivetime.core.TimeFormatExt.timeFormat
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
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(4.dp))
    ) {
        Box {
            Column {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(10.dp),
                    text = task.task,
                    maxLines = 1,
                )
            }
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                Text(
                    modifier = Modifier.padding(start = 10.dp, top = 13.dp),
                    text = initialTotalTimeInMillis.timeFormat()
                )
                IconButton(
                    onClick = {
                        navigator.navigate(TaskTimerScreenDestination(task))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayCircleOutline,
                        contentDescription = stringResource(id = R.string.start_task),
                    )
                }
            }
        }
    }
}