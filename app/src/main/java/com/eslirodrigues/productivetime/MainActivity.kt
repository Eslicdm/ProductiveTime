package com.eslirodrigues.productivetime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.eslirodrigues.productivetime.ui.screen.TaskScreen
import com.eslirodrigues.productivetime.ui.theme.ProductiveTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductiveTimeTheme {
//                TimeToCompose()
                TaskScreen()
//                val navController = rememberNavController()
//
//                NavHost(
//                    navController = navController,
//                    startDestination = ScreenNav.Task.route
//                ) {
//                    composable(route = ScreenNav.Task.route) {
//                        TaskScreen(navController = navController)
//                    }
//                }
            }
        }
    }
}