package com.eslirodrigues.productivetime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.eslirodrigues.productivetime.ui.screen.NavGraphs
import com.eslirodrigues.productivetime.ui.theme.ProductiveTimeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductiveTimeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}