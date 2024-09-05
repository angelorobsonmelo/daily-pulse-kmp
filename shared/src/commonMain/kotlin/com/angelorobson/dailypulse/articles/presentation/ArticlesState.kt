package com.angelorobson.dailypulse.articles.presentation

import com.angelorobson.dailypulse.articles.domain.models.Article

data class ArticlesState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
