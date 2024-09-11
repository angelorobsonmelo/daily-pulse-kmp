package com.angelorobson.dailypulse.android.modules.article


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
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

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = message, style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}






