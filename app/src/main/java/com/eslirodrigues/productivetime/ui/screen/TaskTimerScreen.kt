package com.eslirodrigues.productivetime.ui.screen

import android.os.CountDownTimer
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PauseCircleOutline
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.productivetime.R
import com.eslirodrigues.productivetime.core.TimeFormatExt.timeFormat
import com.eslirodrigues.productivetime.data.datasource.Task
import com.eslirodrigues.productivetime.ui.theme.LightOrange
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.concurrent.TimeUnit

private var countDownTimer: CountDownTimer? = null

private var initialTotalTimeInMillis: Long? = null

private var timeLeft: MutableState<Long>? = null
private var textTimer: MutableState<String>? = null
private var isPlaying: MutableState<Boolean>? = null

@ExperimentalMaterialApi
@Destination
@Composable
fun TaskTimerScreen(
    task: Task,
    navigator: DestinationsNavigator
) {
    BackHandler {
        resetTimer()
        navigator.popBackStack()
    }

    isPlaying = remember { mutableStateOf(false) }

    val hourToMillis = TimeUnit.HOURS.toMillis(task.hour)
    val minToMillis = TimeUnit.MINUTES.toMillis(task.min)

    initialTotalTimeInMillis = hourToMillis + minToMillis
    timeLeft = remember { mutableStateOf(initialTotalTimeInMillis!!) }

    textTimer = remember { mutableStateOf(timeLeft!!.value.timeFormat()) }

    Column(
        modifier = Modifier
            .background(LightOrange)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {
                Text(stringResource(id = R.string.task_timer), color = Color.White)
            },
            backgroundColor = LightOrange,
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigator.popBackStack()
                    }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, stringResource(id = R.string.back), tint = Color.White)
                }
            }
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 13.dp),
            text = textTimer!!.value,
            fontSize = 100.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(20.dp))
        IconButton(
            onClick = { resetTimer() },
        ) {
            Icon(
                Icons.Default.Replay,
                contentDescription = stringResource(id = R.string.add_task),
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        IconButton(
            onClick = {
                if (isPlaying!!.value) stopTimer() else startTimer()
                isPlaying!!.value = !isPlaying!!.value
            },
            modifier = Modifier.size(100.dp)
        ) {
            if (isPlaying!!.value) {
                Icon(
                    imageVector = Icons.Default.PauseCircleOutline,
                    contentDescription = stringResource(id = R.string.add_task),
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.PlayCircleOutline,
                    contentDescription = stringResource(id = R.string.add_task),
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}

fun startTimer() {
    countDownTimer = object : CountDownTimer(timeLeft!!.value, 1000L) {
        override fun onTick(p0: Long) {
            textTimer!!.value = p0.timeFormat()
            timeLeft!!.value = p0
        }

        override fun onFinish() {
            textTimer!!.value = initialTotalTimeInMillis!!.timeFormat()
        }
    }.start()
}


private fun stopTimer() {
    countDownTimer?.cancel()
}

private fun resetTimer() {
    countDownTimer?.cancel()
    isPlaying!!.value = false
    timeLeft!!.value = initialTotalTimeInMillis!!
    textTimer!!.value = initialTotalTimeInMillis!!.timeFormat()
}