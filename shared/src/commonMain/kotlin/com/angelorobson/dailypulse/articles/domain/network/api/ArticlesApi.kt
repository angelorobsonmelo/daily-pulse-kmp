package com.angelorobson.dailypulse.articles.domain.network.api

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse

interface ArticlesApi {

    suspend fun fetchArticles(): List<ArticleRawResponse>
}