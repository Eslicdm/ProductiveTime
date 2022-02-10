package com.eslirodrigues.productivetime.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.eslirodrigues.productivetime.core.notification.TaskNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val taskNotification: TaskNotification,
) : ViewModel() {

    fun feedNotification(context: Context, textTitle: String, textContent: String) {
        taskNotification.createNotificationWithTapAction(
            context = context,
            textTitle = textTitle,
            textContent =  textContent
        )
    }
}