package com.eslirodrigues.productivetime.data.datasource

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Long,
    val task: String,
    val hour: Long,
    val min: Long
) : Parcelable