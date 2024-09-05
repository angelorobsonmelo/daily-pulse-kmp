package com.angelorobson.dailypulse.articles.domain.repositories

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse

interface ArticlesRepository {

    suspend fun getArticles(forceFetch: Boolean): List<ArticleRawResponse>
}