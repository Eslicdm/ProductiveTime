package com.eslirodrigues.productivetime.core

sealed class ScreenNav(val route: String) {
    object Task : ScreenNav(route = "task_screen")
}