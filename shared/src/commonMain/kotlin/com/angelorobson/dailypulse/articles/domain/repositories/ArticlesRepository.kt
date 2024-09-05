package com.angelorobson.dailypulse.articles.domain.repositories

import com.angelorobson.dailypulse.articles.domain.models.Article

interface ArticlesRepository {

    suspend fun getLocalArticles(): List<Article>
    suspend fun fetchRemoteArticles(): List<Article>
    suspend fun clearLocalArticles()
    suspend fun createArticles(articles: List<Article>)
}