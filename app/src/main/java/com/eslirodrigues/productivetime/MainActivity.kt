package com.eslirodrigues.productivetime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.productivetime.core.ScreenNav
import com.eslirodrigues.productivetime.ui.screen.AddTaskScreen
import com.eslirodrigues.productivetime.ui.screen.TaskScreen
import com.eslirodrigues.productivetime.ui.theme.ProductiveTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductiveTimeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ScreenNav.Task.route
                ) {
                    composable(route = ScreenNav.Task.route) {
                        TaskScreen(navController = navController)
                    }
                    composable(route = ScreenNav.AddTask.route) {
                        AddTaskScreen(navController = navController)
                    }
                }
            }
        }
    }
}

