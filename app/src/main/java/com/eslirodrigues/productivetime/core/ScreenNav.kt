package com.eslirodrigues.productivetime.core

sealed class ScreenNav(val route: String) {
    object Task : ScreenNav(route = "task_screen")
    object AddTask : ScreenNav(route = "add_task_screen")
}