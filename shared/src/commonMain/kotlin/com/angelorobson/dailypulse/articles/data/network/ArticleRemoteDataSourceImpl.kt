package com.angelorobson.dailypulse.articles.data.network

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.articles.domain.network.ArticleRemoteDataSource
import com.angelorobson.dailypulse.articles.domain.network.api.ArticlesApi

class ArticleRemoteDataSourceImpl(
    private val articlesApi: ArticlesApi
) : ArticleRemoteDataSource {

    override suspend fun fetchArticles(): List<ArticleRawResponse> = articlesApi.fetchArticles()
}