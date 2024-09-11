package com.angelorobson.dailypulse.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.angelorobson.dailypulse.android.navigation.AppNavHostGraph

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHostGraph(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}