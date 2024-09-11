package com.angelorobson.dailypulse.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.angelorobson.dailypulse.android.modules.about.AboutScreen
import com.angelorobson.dailypulse.android.modules.article.ArticlesScreen
import com.angelorobson.dailypulse.android.modules.sources.SourcesScreen

@Composable
fun AppNavHostGraph(
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