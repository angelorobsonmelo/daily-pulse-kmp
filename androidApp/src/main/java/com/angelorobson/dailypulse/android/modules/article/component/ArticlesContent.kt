package com.angelorobson.dailypulse.android.modules.article.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.angelorobson.dailypulse.android.modules.article.ErrorMessage
import com.angelorobson.dailypulse.articles.presentation.ArticlesState

@Composable
fun ArticlesContent(
    articlesState: ArticlesState,
    onAboutButtonClick: () -> Unit,
    onSourcesButtonClick: () -> Unit,
    onForceLoad: () -> Unit
) {
    Column {
        ArticlesAppBar(onAboutButtonClick, onSourcesButtonClick)

        if (articlesState.error != null) ErrorMessage(articlesState.error.toString())
        if (articlesState.articles.isNotEmpty()) ArticlesListView(articlesState) {
            onForceLoad()
        }
    }
}