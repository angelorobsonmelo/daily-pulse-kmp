package com.angelorobson.dailypulse.android.modules.article.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angelorobson.dailypulse.articles.presentation.ArticlesState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun ArticlesListView(state: ArticlesState, onSwipeToRefresh: () -> Unit) {
    SwipeRefresh(state = SwipeRefreshState(state.loading), onRefresh = {
        onSwipeToRefresh()
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.articles) { article ->
                ArticleItemView(article = article)
            }
        }
    }

}