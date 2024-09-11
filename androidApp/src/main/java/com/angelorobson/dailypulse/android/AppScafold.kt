package com.angelorobson.dailypulse.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelorobson.dailypulse.android.screens.AboutScreen
import com.angelorobson.dailypulse.android.screens.ArticlesScreen
import com.angelorobson.dailypulse.android.screens.Screens
import com.angelorobson.dailypulse.android.screens.SourcesScreen

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Articles,
        modifier = modifier,
    ) {
        composable<Screens.Articles> {
            ArticlesScreen(
                onAboutButtonClick = { navController.navigate(Screens.AboutDevice) },
                onSourcesButtonClick = { navController.navigate(Screens.Sources) }
            )
        }

        composable<Screens.Sources> {
            SourcesScreen(
                onUpButtonClick = { navController.popBackStack() }
            )
        }

        composable<Screens.AboutDevice> {
            AboutScreen(
                onUpButtonClick = { navController.popBackStack() }
            )
        }
    }
}