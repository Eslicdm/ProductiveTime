package com.eslirodrigues.productivetime.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import productivetime.taskdb.TaskEntity

@Composable
fun TaskItemScreen(task: TaskEntity) {

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(4.dp))
    ) {
        Row {
            Text(
                modifier = Modifier.padding(10.dp),
                text = task.task
            )
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                text = task.hour.toString()
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = ":${task.min.toString()}"
            )
        }
    }
}