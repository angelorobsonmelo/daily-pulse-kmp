package com.angelorobson.dailypulse.articles.domain.local

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse

interface ArticlesLocalDataSource {

    fun getAllArticles(): List<ArticleRawResponse>

    fun insertArticles(articles: List<ArticleRawResponse>)

    fun clearArticles()
}