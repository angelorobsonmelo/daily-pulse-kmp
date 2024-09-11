package com.angelorobson.dailypulse.android.modules.article


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.angelorobson.dailypulse.android.modules.article.components.ArticlesContent
import com.angelorobson.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesScreen(
    onAboutButtonClick: () -> Unit,
    onSourcesButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel = getViewModel(),
) {
    val articlesState by articlesViewModel.articlesState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        articlesViewModel.getArticles()
    }

    ArticlesContent(
        articlesState = articlesState,
        onAboutButtonClick = onAboutButtonClick,
        onSourcesButtonClick = onSourcesButtonClick,
    ) {
        articlesViewModel.getArticles(true)
    }
}






