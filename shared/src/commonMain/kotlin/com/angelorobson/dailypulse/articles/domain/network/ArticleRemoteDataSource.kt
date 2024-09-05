package com.angelorobson.dailypulse.articles.domain.network

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse

interface ArticleRemoteDataSource {

    suspend fun fetchArticles(): List<ArticleRawResponse>
}